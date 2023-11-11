package com.example.emsolution.back

import android.util.Patterns

object Utils {
    private fun checkPassword(pass: String): Boolean {
        return pass.length in 6..15 && !pass.contains(" ") && pass.all { it.isLetterOrDigit() }
    }

    private fun checkEmail(email: String): Boolean {
        return email.isNotEmpty() && email.length <= 30 && Patterns.EMAIL_ADDRESS.matcher(email)
            .matches() && !email.contains(" ")
    }

    fun checkFieldsUser(user: String, pass: String): Boolean {
        return checkEmail(user) && checkPassword(pass)
    }

    fun checkFieldsUpdate(
        title: String,
        description: String,
        amount: String,
        price: String
    ): Boolean {
        return !(title.isEmpty() || description.isEmpty() || amount.isEmpty() || price.isEmpty())
    }

    fun checkFieldsAdd(
        barcode: String,
        title: String,
        description: String,
        amount: String,
        price: String
    ): Boolean {
        return !( barcode.isEmpty() || title.isEmpty() || description.isEmpty() || amount.isEmpty() || price.isEmpty())
    }

}
