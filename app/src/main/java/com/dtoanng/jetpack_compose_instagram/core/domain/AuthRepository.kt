package com.dtoanng.jetpack_compose_instagram.core.domain

import android.net.Uri
import com.dtoanng.jetpack_compose_instagram.core.data.firebase.UserDto
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun createUserWithEmailAndPassword(email: String, password: String): FirebaseUser?
    suspend fun isUsernameAvailable(username: String): Boolean
    suspend fun saveUserProfile(userDto: UserDto)
    suspend fun uploadProfileImage(imageUri: Uri): String
    suspend fun signInWithEmailAndPassword(email: String, password: String): FirebaseUser?
}