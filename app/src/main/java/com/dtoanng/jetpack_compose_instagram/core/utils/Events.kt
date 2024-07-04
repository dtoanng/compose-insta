package com.dtoanng.jetpack_compose_instagram.core.utils

import android.net.Uri
import com.dtoanng.jetpack_compose_instagram.core.data.firebase.UserDto

sealed class ResultEvents<out T> {
    data object OnIdle : ResultEvents<Nothing>()
    data object OnLoading : ResultEvents<Nothing>()
    data class OnError(val error: String) : ResultEvents<Nothing>()
    data class OnSuccess<T>(val message: T) : ResultEvents<T>()
}

sealed class AuthEvents {
    data class OnSignIn(val email: String, val password: String) : AuthEvents()
    data class OnSignUp(val imageUrl: Uri?, val userDto: UserDto) : AuthEvents()
}