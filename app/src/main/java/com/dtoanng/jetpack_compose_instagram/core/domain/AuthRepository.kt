package com.dtoanng.jetpack_compose_instagram.core.domain

import com.dtoanng.jetpack_compose_instagram.core.data.firebase.UserDto
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun createUserWithEmailAndPassword(email: String, password: String): FirebaseUser?
    suspend fun isUsernameAvailable(username: String): Boolean
    suspend fun saveUserProfile(userDto: UserDto)
}