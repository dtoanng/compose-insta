package com.dtoanng.jetpack_compose_instagram.core.utils

import com.dtoanng.jetpack_compose_instagram.core.data.firebase.UserDto
import java.util.regex.Pattern

object AuthValidator {

    fun validateCreatingUser(createdUserDto: UserDto): ValidateResult {
        val username = createdUserDto.username
        val password = createdUserDto.password
        val email = createdUserDto.email
        val fullName = createdUserDto.fullName

        if (username.isBlank() && password.isBlank() && email.isBlank() && fullName.isBlank()) {
            return ValidateResult(
                successful = false,
                error = "All field are empty."
            )
        }

        if (email.isBlank()) return ValidateResult(
            successful = false,
            error = "Email cannot be blank."
        )

        if (email.isNotBlank()) {
            val emailRegex = Pattern.compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
            )
            val matches = emailRegex.matcher(email).matches()
            if (!matches) {
                return ValidateResult(
                    successful = false,
                    error = "Email is not valid."
                )
            }
        }

        if (fullName.isBlank()) return ValidateResult(successful = false, error = "FullName cannot be blank.")
        if (username.isBlank()) return ValidateResult(successful = false, error = "Username cannot be blank.")
        if (password.isBlank()) return ValidateResult(successful = false, error = "Password cannot be blank.")
        if (password.isNotBlank()) {
            if (password.length < 6) return ValidateResult(successful = false, error = "Password must be at least 6 characters.")
            else {
                val passwordRegex = Pattern.compile(
                    "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
                )

                val matches = passwordRegex.matcher(password).matches()
                if(!matches) return ValidateResult(
                    successful = false,
                    error = "Your password is too weak."
                )
            }
        }

        return ValidateResult(successful = true)
    }

    fun validateSignIn(): ValidateResult {

        return ValidateResult(successful = true)
    }
}