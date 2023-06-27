package com.dojo.globant.mymessenger.feature.sign_in.data.repositories

import com.dojo.globant.mymessenger.common.util.Resource
import com.dojo.globant.mymessenger.core.datastore.UserManager
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@ViewModelScoped
class SignInRepository @Inject constructor(
    private val userManager: UserManager,
    private val firebaseAuth: FirebaseAuth
) {

    /*@Throws(Exception::class)
    suspend fun signInWithEmailAndPasswordException(
        email: String,
        password: String,
        onResult: (Throwable?) -> Unit
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    onResult(Exception())
                }
            }
        userManager.saveUserEmail(email)
        userManager.saveIsLogged(true)
    }

    fun signInWithCallbackFlow(email: String, password: String) = callbackFlow {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    trySend(false)
                } else
                    trySend(true)
            }
        awaitClose()
    }*/

    fun signInWithEmailAndPassword(email: String, password: String) = flow<Resource<AuthResult>> {
        emit(Resource.Loading())
        val result = firebaseAuth.signInWithEmailAndPassword(email, password)
            .await()
        emit(Resource.Success(result))
    }.catch {
        emit(Resource.Error(it.message.toString()))
    }


    suspend fun isUserLogged() = userManager.getIsLogged().first()
}