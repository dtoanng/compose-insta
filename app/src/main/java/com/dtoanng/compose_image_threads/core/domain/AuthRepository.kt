package com.dtoanng.compose_image_threads.core.domain

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun createUserWithEmailAndPassword(email: String, password: String): FirebaseUser?
}