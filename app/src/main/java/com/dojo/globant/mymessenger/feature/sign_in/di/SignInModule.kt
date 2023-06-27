package com.dojo.globant.mymessenger.feature.sign_in.di

import com.dojo.globant.mymessenger.core.datastore.UserManager
import com.dojo.globant.mymessenger.feature.sign_in.data.repositories.SignInRepository
import com.dojo.globant.mymessenger.feature.sign_in.domain.usecase.SignInUseCase
import com.dojo.globant.mymessenger.feature.sign_in.domain.usecase.ValidateFieldsUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers

@InstallIn(ViewModelComponent::class)
@Module
object SignInModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideSignInUseCase(repository: SignInRepository): SignInUseCase =
        SignInUseCase(repository, Dispatchers.IO)

    @Provides
    fun provideValidateFieldsUseCase(): ValidateFieldsUseCase =
        ValidateFieldsUseCase()

    @Provides
    fun provideSignInRepository(
        userManager: UserManager,
        firebaseAuth: FirebaseAuth
    ): SignInRepository = SignInRepository(userManager, firebaseAuth)
}