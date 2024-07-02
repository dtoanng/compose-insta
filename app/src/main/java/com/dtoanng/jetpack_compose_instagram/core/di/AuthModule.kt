package com.dtoanng.jetpack_compose_instagram.core.di

import com.dtoanng.jetpack_compose_instagram.core.data.firebase.FirebaseAuthObjectsImpl
import com.dtoanng.jetpack_compose_instagram.core.data.repository.AuthRepositoryImpl
import com.dtoanng.jetpack_compose_instagram.core.domain.AuthRepository
import com.dtoanng.jetpack_compose_instagram.core.domain.IFirebaseAuthenticator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Singleton
    @Provides
    fun provideFirebaseObjectsAuthenticator(): IFirebaseAuthenticator = FirebaseAuthObjectsImpl()

    @Singleton
    @Provides
    fun provideAuthRepository(fireBaseObjects: IFirebaseAuthenticator): AuthRepository = AuthRepositoryImpl(fireBaseObjects)
}