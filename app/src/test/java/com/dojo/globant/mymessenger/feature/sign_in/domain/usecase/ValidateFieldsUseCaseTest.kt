package com.dojo.globant.mymessenger.feature.sign_in.domain.usecase

import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class ValidateFieldsUseCaseTest {

    private lateinit var validateFields: ValidateFieldsUseCase

    @Before
    fun setUp() {
        validateFields = ValidateFieldsUseCase()
    }

    @Test
    fun `When the phone is empty returns error`() {
        val result = validateFields.validatePhone("")
        assertEquals(false, result.successful)
        assertNotEquals(null, result.errorMessage)
    }

    @Test
    fun `When the phone only contains spaces returns error`() {
        val result = validateFields.validatePhone("   ")
        assertEquals(false, result.successful)
        assertNotEquals(null, result.errorMessage)
    }

    @Test
    fun `When the phone contains others characters, no digits, returns error`() {
        val result = validateFields.validatePhone("123d56")
        assertEquals(false, result.successful)
        assertNotEquals(null, result.errorMessage)
    }

    @Test
    fun `When the phone contains less than 10 digits returns error`() {
        val result = validateFields.validatePhone("1234")
        assertEquals(false, result.successful)
        assertNotEquals(null, result.errorMessage)
    }

    @Test
    fun `When the phone contains more than 10 digits returns error`() {
        val result = validateFields.validatePhone("1234567890123")
        assertEquals(false, result.successful)
        assertNotEquals(null, result.errorMessage)
    }

    @Test
    fun `When the phone contains exactly 10 digits returns success`() {
        val result = validateFields.validatePhone("1234567890")
        assertEquals(true, result.successful)
        assertEquals(null, result.errorMessage)
    }

    @Test
    fun `When the password is empty returns error`() {
        val result = validateFields.validatePassword("")
        assertEquals(false, result.successful)
        assertNotEquals(null, result.errorMessage)
    }

    @Test
    fun `When the password only contains spaces returns error`() {
        val result = validateFields.validatePassword("   ")
        assertEquals(false, result.successful)
        assertNotEquals(null, result.errorMessage)
    }

    @Test
    fun `When the password contains at least 1 character returns sucess`() {
        val result = validateFields.validatePassword("*")
        assertEquals(true, result.successful)
        assertEquals(null, result.errorMessage)
    }

}