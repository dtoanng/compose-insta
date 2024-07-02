package com.dtoanng.jetpack_compose_instagram.core.presentation

import androidx.lifecycle.ViewModel
import com.dtoanng.jetpack_compose_instagram.core.domain.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JetInstagramViewModel @Inject constructor(
    authRepository: AuthRepository
): ViewModel() {

}