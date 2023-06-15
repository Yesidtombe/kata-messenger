package com.dojo.globant.mymessenger.feature.home.list.di

import com.dojo.globant.mymessenger.feature.home.list.data.repository.ListRepository
import com.dojo.globant.mymessenger.feature.home.list.domain.usecase.GetAllChatsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers

@InstallIn(ViewModelComponent::class)
@Module
object ListModule {

    @Provides
    fun provideGetAllChatsUseCase(repository: ListRepository): GetAllChatsUseCase =
        GetAllChatsUseCase(repository, Dispatchers.IO)

}