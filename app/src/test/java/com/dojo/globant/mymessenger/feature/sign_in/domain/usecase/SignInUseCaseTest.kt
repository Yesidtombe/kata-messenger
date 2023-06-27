package com.dojo.globant.mymessenger.feature.sign_in.domain.usecase

import com.dojo.globant.mymessenger.common.util.Resource
import com.dojo.globant.mymessenger.feature.sign_in.data.repositories.SignInRepository
import com.google.firebase.auth.AuthResult
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SignInUseCaseTest {
    @MockK
    private lateinit var signInRepository: SignInRepository

    private lateinit var signInUseCase: SignInUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        signInUseCase = SignInUseCase(signInRepository, Dispatchers.IO)
    }

    @Test
    fun `if the user is logged return true`() = runBlocking {
        // Given
        val isUserLogged = true

        coEvery { signInRepository.isUserLogged() } returns isUserLogged

        // When
        var response: Boolean? = null
        signInUseCase.isUserLogged().collect {
            response = it
        }

        // Then
        coVerify(exactly = 1) { signInRepository.isUserLogged() }
        assertTrue(response!!)
    }

    @Test
    fun `when the user signs in successful returns first a Resource of type Loading`() = runBlocking {
        // Given
        val email = "test@gmail.com"
        val password = "123456"

        every { signInRepository.signInWithEmailAndPassword(email, password) } returns flowOf(Resource.Loading())

        // When
        var response: Resource<AuthResult>? = null
        signInUseCase.signIn(email, password).collect {
            response = it
        }

        // Then
        verify(exactly = 1) { signInRepository.signInWithEmailAndPassword(any(), any()) }
        assertNotNull(response)
        assertTrue(response is Resource.Loading)
    }

    @Test
    fun `when the user sign in wrongly returns a Resource of type Error`() = runBlocking {
        // Given
        val email = "test@gmail.com"
        val password = "123456"

        every { signInRepository.signInWithEmailAndPassword(email, password) } returns flowOf(Resource.Error("There was an error"))

        // When
        var response: Resource<AuthResult>? = null
        signInUseCase.signIn(email, password).collect {
            response = it
        }

        // Then
        verify(exactly = 1) { signInRepository.signInWithEmailAndPassword(any(), any()) }
        assertNotNull(response)
        assertTrue(response is Resource.Error)
    }

}