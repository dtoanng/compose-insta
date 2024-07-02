package com.dtoanng.jetpack_compose_instagram.core.data.firebase

import com.google.firebase.firestore.FieldValue

data class UserDto(
    val uid: String = "",
    val username: String,
    val password: String,
    val email: String,
    val fullName: String,
    val imageUrl: String = "",
    val createdDate: FieldValue? = null,
)
