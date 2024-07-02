package com.dtoanng.jetpack_compose_instagram.core.data.repository

import com.dtoanng.jetpack_compose_instagram.core.data.firebase.UserDto
import com.dtoanng.jetpack_compose_instagram.core.domain.AuthRepository
import com.dtoanng.jetpack_compose_instagram.core.domain.IFirebaseAuthenticator
import com.dtoanng.jetpack_compose_instagram.core.utils.CoreConstants
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val fireBaseObjects: IFirebaseAuthenticator
) : AuthRepository {
    override suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String
    ): FirebaseUser? {

        fireBaseObjects.getFirebaseAuth().run {
            createUserWithEmailAndPassword(email, password).await()
            currentUser?.sendEmailVerification()?.await()
        }

        return fireBaseObjects.getFirebaseAuth().currentUser
    }

    override suspend fun isUsernameAvailable(username: String): Boolean {
        val querySnapshot = fireBaseObjects
            .getFirebaseFirestore()
            .collection(CoreConstants.USERS)
            .whereEqualTo(CoreConstants.USERNAME, username)
            .get()
            .await()

        return querySnapshot.documents.map {
            it.getString(CoreConstants.USERNAME)
        }.none {
            it.equals(username, true)
        }
    }

    override suspend fun saveUserProfile(userDto: UserDto) {
        fireBaseObjects
            .getFirebaseFirestore()
            .collection(CoreConstants.USERS)
            .document(userDto.uid)
            .set(userDto)
            .await()
    }
}