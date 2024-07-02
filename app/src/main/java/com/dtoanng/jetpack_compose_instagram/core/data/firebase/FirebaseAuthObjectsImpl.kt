package com.dtoanng.jetpack_compose_instagram.core.data.firebase

import com.dtoanng.jetpack_compose_instagram.core.domain.IFirebaseAuthenticator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class FirebaseAuthObjectsImpl: IFirebaseAuthenticator {
    override fun getFirebaseAuth(): FirebaseAuth {
        return Firebase.auth
    }

    override fun getFirebaseFirestore(): FirebaseFirestore {
        return Firebase.firestore
    }

    override fun getFirebaseStorage(): FirebaseStorage {
        return Firebase.storage
    }
}