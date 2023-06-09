package com.dojo.globant.mymessenger.core.di

import android.content.Context
import com.dojo.globant.mymessenger.core.datastore.UserManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideUserManager(@ApplicationContext context: Context) =
        UserManager(context)
}