package com.dojo.globant.mymessenger.feature.sign_in.domain.usecase

import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class ValidateFieldsUseCaseTest {

    private lateinit var validateFields: ValidateFieldsUseCase

    @Before
    fun setUp() {
        validateFields = ValidateFieldsUseCase()
    }

    @Test
    fun `When the email is empty returns error`() {
        val result = validateFields.validateEmail("")
        assertFalse(result.successful)
        assertNotNull(result.errorMessage)
    }

    @Test
    fun `When the email only contains spaces returns error`() {
        val result = validateFields.validateEmail("   ")
        assertFalse(result.successful)
        assertNotNull(result.errorMessage)
    }

    @Test
    fun `When the email does not contain @ returns error`() {
        val result = validateFields.validateEmail("example.com")
        assertFalse(result.successful)
        assertNotNull(result.errorMessage)
    }

    @Test
    fun `When the email does not contain a domain returns error`() {
        val result = validateFields.validateEmail("example@.com")
        assertFalse(result.successful)
        assertNotNull(result.errorMessage)
    }

    @Test
    fun `When the email domain extension contains 1 character returns error`() {
        val result = validateFields.validateEmail("example@domain.c")
        assertFalse(result.successful)
        assertNotNull(result.errorMessage)
    }

    @Test
    fun `When the email domain extension contains more than 3 characters returns error`() {
        val result = validateFields.validateEmail("example@domain.coms")
        assertFalse(result.successful)
        assertNotNull(result.errorMessage)
    }

    @Test
    fun `When the second email domain extension contains 1 character returns error`() {
        val result = validateFields.validateEmail("example@domain.com.c")
        assertFalse(result.successful)
        assertNotNull(result.errorMessage)
    }

    @Test
    fun `When the second email domain extension contains more than 3 characters returns error`() {
        val result = validateFields.validateEmail("example@domain.com.cons")
        assertFalse(result.successful)
        assertNotNull(result.errorMessage)
    }

    @Test
    fun `When the email contains brackets returns error`() {
        val result = validateFields.validateEmail("exam(ple@domain.com.co")
        assertFalse(result.successful)
        assertNotNull(result.errorMessage)
    }

    @Test
    fun `When the email contains special characters returns error`() {
        val result = validateFields.validateEmail("example*@domain.com.co")
        assertFalse(result.successful)
        assertNotNull(result.errorMessage)
    }

    @Test
    fun `When the email contains 1 domain extension with 3 characters returns success`() {
        val result = validateFields.validateEmail("example@domain.com")
        assertTrue(result.successful)
        assertNull(result.errorMessage)
    }

    @Test
    fun `When the email contains 1 domain extension with 2 characters returns success`() {
        val result = validateFields.validateEmail("example@domain.co")
        assertTrue(result.successful)
        assertNull(result.errorMessage)
    }

    @Test
    fun `When the email contains 2 domain extensions returns success`() {
        val result = validateFields.validateEmail("example@domain.com.co")
        assertTrue(result.successful)
        assertNull(result.errorMessage)
    }


    @Test
    fun `When the password is empty returns error`() {
        val result = validateFields.validatePassword("")
        assertFalse(result.successful)
        assertNotNull(result.errorMessage)
    }

    @Test
    fun `When the password only contains spaces returns error`() {
        val result = validateFields.validatePassword("   ")
        assertFalse(result.successful)
        assertNotNull(result.errorMessage)
    }

    @Test
    fun `When the password contains at least 1 character returns success`() {
        val result = validateFields.validatePassword("*")
        assertTrue(result.successful)
        assertNull(result.errorMessage)
    }

}