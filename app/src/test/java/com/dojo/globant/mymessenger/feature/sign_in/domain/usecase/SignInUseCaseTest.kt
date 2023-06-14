package com.dojo.globant.mymessenger.feature.sign_in.domain.usecase

import com.dojo.globant.mymessenger.feature.sign_in.data.repositories.SignInRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
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
        val isUserLogged = flowOf(true)

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
    fun `when the user sign in successful return true`() = runBlocking {
        // Given
        val phone = "3113334455"
        val saveUserLogged = flowOf(true)

        coEvery { signInRepository.saveSession(phone) } returns saveUserLogged

        // When
        var response: Boolean? = null
        signInUseCase.signIn(phone).collect {
            response = it
        }

        // Then
        coVerify(exactly = 1) { signInRepository.saveSession(any()) }
        assertTrue(response!!)
    }

}