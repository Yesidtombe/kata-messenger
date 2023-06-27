package com.dojo.globant.mymessenger.feature.sign_in.domain.usecase

import com.dojo.globant.mymessenger.R
import com.dojo.globant.mymessenger.common.Constants.REGEX_EMAIL
import com.dojo.globant.mymessenger.common.util.UiText
import com.dojo.globant.mymessenger.common.util.ValidationResult

class ValidateFieldsUseCase {

    fun validateEmail(email: String) : ValidationResult {
        email.trim().apply {
            if (isBlank()) {
                return ValidationResult(
                    successful = false,
                    errorMessage = UiText.StringResource(id = R.string.field_required_error)
                )
            }
            if (!Regex(REGEX_EMAIL).matches(this)) {
                return ValidationResult(
                    successful = false,
                    errorMessage = UiText.StringResource(id = R.string.email_invalid_error)
                )
            }
        }
        return ValidationResult(successful = true)
    }

    fun validatePassword(password: String) : ValidationResult {
        password.trim().apply {
            if (isBlank()) {
                return ValidationResult(
                    successful = false,
                    errorMessage = UiText.StringResource(id = R.string.field_required_error)
                )
            }
        }
        return ValidationResult(successful = true)
    }

}