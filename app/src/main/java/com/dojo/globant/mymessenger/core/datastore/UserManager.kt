package com.dojo.globant.mymessenger.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataUser: DataStore<Preferences> by preferencesDataStore(name = "user")

class UserManager(private val context: Context) {

    companion object {
        private val USER_AUTHENTICATED_KEY = booleanPreferencesKey("USER_AUTHENTICATED")
        private val USER_PHONE_KEY = stringPreferencesKey("USER_PHONE")
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

    suspend fun getIsLogged(): Boolean {
        val prefs = context.dataUser.data.first()
        return prefs[USER_AUTHENTICATED_KEY] ?: false
    }

}