package com.dojo.globant.mymessenger.feature.home.profile.di

import com.dojo.globant.mymessenger.core.datastore.UserManager
import com.dojo.globant.mymessenger.feature.home.profile.data.repository.GetSessionRepository
import com.dojo.globant.mymessenger.feature.home.profile.domain.usecase.GetSessionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers

@InstallIn(ViewModelComponent::class)
@Module
object ProfileModule {

    @Provides
    fun provideGetSessionUseCase(repository: GetSessionRepository): GetSessionUseCase =
        GetSessionUseCase(repository, Dispatchers.IO)

    @Provides
    fun provideGetSessionRepository(userManager: UserManager): GetSessionRepository =
        GetSessionRepository(userManager)

}