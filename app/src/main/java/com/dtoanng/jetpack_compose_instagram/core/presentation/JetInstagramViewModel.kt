package com.dtoanng.jetpack_compose_instagram.core.presentation

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dtoanng.jetpack_compose_instagram.core.data.firebase.UserDto
import com.dtoanng.jetpack_compose_instagram.core.domain.AuthRepository
import com.dtoanng.jetpack_compose_instagram.core.utils.AuthEvents
import com.dtoanng.jetpack_compose_instagram.core.utils.AuthValidator
import com.dtoanng.jetpack_compose_instagram.core.utils.ResultEvents
import com.google.firebase.firestore.FieldValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JetInstagramViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<ResultEvents<String>>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onUserEvents(authEvents: AuthEvents) {
        when (authEvents) {
            is AuthEvents.OnSignIn -> {}
            is AuthEvents.OnSignUp -> {

                val res = AuthValidator.validateCreatingUser(authEvents.userDto)
                if (res.successful)
                    createUser(imageUri = authEvents.imageUrl, userDto = authEvents.userDto)
                else viewModelScope.launch {
                    _eventFlow.emit(ResultEvents.OnError(res.error!!))
                }
            }
        }
    }

    private fun createUser(imageUri: Uri?, userDto: UserDto) {
        _eventFlow.tryEmit(ResultEvents.OnLoading)
        try {
            viewModelScope.launch {
                val usernameAvailability = authRepository.isUsernameAvailable(userDto.username)
                if (!usernameAvailability)
                    _eventFlow.emit(ResultEvents.OnError("Username is not available, try another one."))
                else {

                    if (imageUri == null) {
                        _eventFlow.emit(ResultEvents.OnError("You've not selected a profile picture yet."))
                    } else {

                        val firebaseUser = authRepository.createUserWithEmailAndPassword(userDto.email, userDto.password)
                        val imageUrl = authRepository.uploadProfileImage(imageUri = imageUri)

                        firebaseUser?.let { user ->
                            val updateUser = userDto.copy(
                                uid = user.uid,
                                createdDate = FieldValue.serverTimestamp(),
                                imageUrl = imageUrl,
                                password = ""
                            )

                            authRepository.saveUserProfile(updateUser)
                        }
                        _eventFlow.emit(ResultEvents.OnSuccess("User created, kindly verify your mail to login"))
                    }
                }
            }
        } catch (e: Exception) {
            _eventFlow.tryEmit(
                ResultEvents.OnError(
                    e.localizedMessage ?: "Unable to create account. Please try again."
                )
            )
        }
    }

}