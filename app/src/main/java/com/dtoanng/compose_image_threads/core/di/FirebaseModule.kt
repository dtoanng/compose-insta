package com.dtoanng.compose_image_threads.core.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class FirebaseModule {
    @Provides
    fun provideAuthentication() = Firebase.auth

    @Provides
    fun provideFirestore() = Firebase.firestore

    @Provides
    fun provideStorage() = Firebase.storage
}