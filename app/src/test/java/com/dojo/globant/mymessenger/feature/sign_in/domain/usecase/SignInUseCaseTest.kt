package com.dojo.globant.mymessenger.feature.sign_in.domain.usecase

import com.dojo.globant.mymessenger.feature.sign_in.data.repositories.SignInRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before

class SignInUseCaseTest {
    @MockK
    private lateinit var signInRepository: SignInRepository

    private lateinit var signInUseCase: SignInUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        signInUseCase = SignInUseCase(signInRepository)
    }
}