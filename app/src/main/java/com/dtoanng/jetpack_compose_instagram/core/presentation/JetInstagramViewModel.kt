package com.dtoanng.jetpack_compose_instagram.core.presentation

import android.annotation.SuppressLint
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dtoanng.jetpack_compose_instagram.core.data.firebase.UserDto
import com.dtoanng.jetpack_compose_instagram.core.domain.AuthRepository
import com.dtoanng.jetpack_compose_instagram.core.utils.AuthEvents
import com.dtoanng.jetpack_compose_instagram.core.utils.ResultEvents
import com.google.firebase.firestore.FieldValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JetInstagramViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _eventFlow = MutableStateFlow<ResultEvents<String>>(ResultEvents.OnIdle)
    val eventFlow = _eventFlow.asStateFlow()

    fun onUserEvents(authEvents: AuthEvents) {
        when (authEvents) {
            is AuthEvents.OnSignIn -> {}
            is AuthEvents.OnSignUp -> {
                createUser(imageUri = authEvents.imageUrl, userDto = authEvents.userDto)
            }
        }
    }

    fun createUser(imageUri: Uri?, userDto: UserDto) {
        _eventFlow.value = ResultEvents.OnLoading
        try {
            viewModelScope.launch {
                val usernameAvailability = authRepository.isUsernameAvailable(userDto.username)
                if (!usernameAvailability)
                    _eventFlow.value = ResultEvents.OnError("Username is not available, try another one.")
                else {
                    val firebaseUser = authRepository.createUserWithEmailAndPassword(userDto.email, userDto.password)

                    //todo: update image profile

                    firebaseUser?.let { user ->
                        val updateUser = userDto.copy(
                            uid = user.uid,
                            createdDate = FieldValue.serverTimestamp(),
                            imageUrl = "",
                            password = ""
                        )

                        authRepository.saveUserProfile(updateUser)
                    }
                    _eventFlow.value = ResultEvents.OnSuccess("User created, kindly verify your mail to login")
                }
            }
        } catch (e: Exception) {
            _eventFlow.value = ResultEvents.OnError(
                e.localizedMessage ?: "Unable to create account. Please try again."
            )
        }
    }

}