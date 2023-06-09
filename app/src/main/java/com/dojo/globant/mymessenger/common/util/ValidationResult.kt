package com.dojo.globant.mymessenger.common.util

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null
)