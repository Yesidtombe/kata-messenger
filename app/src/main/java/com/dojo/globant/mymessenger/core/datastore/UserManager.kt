package com.dojo.globant.mymessenger.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataUser: DataStore<Preferences> by preferencesDataStore(name = "user")

class UserManager(private val context: Context) {

    companion object {
        private val USER_AUTHENTICATED_KEY = booleanPreferencesKey("USER_AUTHENTICATED")
        private val USER_PHONE_KEY = stringPreferencesKey("USER_PHONE")
        private val USER_EMAIL_KEY = stringPreferencesKey("USER_EMAIL")
    }

    suspend fun saveUserEmail(email: String) {
        context.dataUser.edit {
            it[USER_EMAIL_KEY] = email
        }
    }

    suspend fun getUserEmail(): String {
        val prefs = context.dataUser.data.first()
        return prefs[USER_EMAIL_KEY].orEmpty()
    }

    suspend fun saveUserPhone(phone: String) {
        context.dataUser.edit {
            it[USER_PHONE_KEY] = phone
        }
    }

    suspend fun getUserPhone(): String {
        val prefs = context.dataUser.data.first()
        return prefs[USER_PHONE_KEY].orEmpty()
    }

    suspend fun saveIsLogged(authenticated: Boolean) {
        context.dataUser.edit {
            it[USER_AUTHENTICATED_KEY] = authenticated
        }
    }

    fun getIsLogged(): Flow<Boolean> = context.dataUser.data.map {
            it[USER_AUTHENTICATED_KEY] ?: false
    }

}