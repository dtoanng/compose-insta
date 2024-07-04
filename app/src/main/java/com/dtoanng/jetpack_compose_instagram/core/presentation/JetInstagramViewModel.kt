package com.dtoanng.jetpack_compose_instagram.core.presentation

import androidx.lifecycle.ViewModel
import com.dtoanng.jetpack_compose_instagram.core.domain.AuthRepository
import com.dtoanng.jetpack_compose_instagram.core.utils.AuthEvents
import com.dtoanng.jetpack_compose_instagram.core.utils.ResultEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class JetInstagramViewModel @Inject constructor(
    authRepository: AuthRepository
): ViewModel() {

    private val _eventFlow = MutableStateFlow(ResultEvents.OnIdle)
    val eventFlow = _eventFlow.asStateFlow()

    fun onUserEvents(authEvents: AuthEvents) {
        when(authEvents) {
            is AuthEvents.OnSignIn -> {}
            is AuthEvents.OnSignUp -> {}
        }
    }

    fun createUser() { /* TODO */ }


}