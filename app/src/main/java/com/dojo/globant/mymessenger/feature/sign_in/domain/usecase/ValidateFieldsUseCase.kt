package com.dojo.globant.mymessenger.feature.sign_in.domain.usecase

import androidx.core.text.isDigitsOnly
import com.dojo.globant.mymessenger.R
import com.dojo.globant.mymessenger.common.util.UiText
import com.dojo.globant.mymessenger.common.util.ValidationResult

class ValidateFieldsUseCase {

    fun validatePhone(phone: String) : ValidationResult {
        phone.trim().apply {
            if (isBlank()) {
                return ValidationResult(
                    successful = false,
                    errorMessage = UiText.StringResource(id = R.string.field_required_error)
                )
            }
            if (!isDigitsOnly()) {
                return ValidationResult(
                    successful = false,
                    errorMessage = UiText.StringResource(id = R.string.phone_invalid_error)
                )
            }
            if (length < 10 || length > 10) {
                return ValidationResult(
                    successful = false,
                    errorMessage = UiText.StringResource(id = R.string.phone_invalid_amount_digits)
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