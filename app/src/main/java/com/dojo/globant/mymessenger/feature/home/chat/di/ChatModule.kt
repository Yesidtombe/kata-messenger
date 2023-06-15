package com.dojo.globant.mymessenger.feature.home.chat.di

import com.dojo.globant.mymessenger.core.datastore.UserManager
import com.dojo.globant.mymessenger.feature.home.chat.data.repository.ChatRepository
import com.dojo.globant.mymessenger.feature.home.chat.domain.usecase.GetAllMessagesUseCase
import com.dojo.globant.mymessenger.feature.home.chat.domain.usecase.SendMessageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers

@InstallIn(ViewModelComponent::class)
@Module
object ChatModule {

    @Provides
    fun provideSendMessageUseCase(repository: ChatRepository, userManager: UserManager): SendMessageUseCase =
        SendMessageUseCase(repository, userManager, Dispatchers.IO)

    @Provides
    fun provideGetAllMessagesUseCase(repository: ChatRepository, userManager: UserManager): GetAllMessagesUseCase =
        GetAllMessagesUseCase(repository, userManager, Dispatchers.IO)

}