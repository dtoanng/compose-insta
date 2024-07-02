package com.dtoanng.jetpack_compose_instagram.core.domain

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

interface IFirebaseAuthenticator {
    fun getFirebaseAuth(): FirebaseAuth
    fun getFirebaseFirestore(): FirebaseFirestore
    fun getFirebaseStorage(): FirebaseStorage
}