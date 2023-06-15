package com.dojo.globant.mymessenger.feature.home.add.di

import android.app.Application
import com.dojo.globant.mymessenger.feature.home.add.data.repository.GetAllContactsRepository
import com.dojo.globant.mymessenger.feature.home.add.data.repository.SaveChatRepository
import com.dojo.globant.mymessenger.feature.home.add.domain.usecase.GetAllContactsUseCase
import com.dojo.globant.mymessenger.feature.home.add.domain.usecase.SaveNewChatUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers

@InstallIn(ViewModelComponent::class)
@Module
object AddModule {

    @Provides
    fun provideGetAllContactsRepository(mApplication: Application): GetAllContactsRepository =
        GetAllContactsRepository(mApplication)

    @Provides
    fun provideGetAllContactsUseCase(repository: GetAllContactsRepository): GetAllContactsUseCase =
        GetAllContactsUseCase(repository)

    @Provides
    fun provideSaveNewChatUseCase(repository: SaveChatRepository): SaveNewChatUseCase =
        SaveNewChatUseCase(repository, Dispatchers.IO)
}