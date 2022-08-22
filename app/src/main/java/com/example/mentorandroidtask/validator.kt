package com.example.mentorandroidtask

import android.util.Patterns

object validator{
    fun emailValidation(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
    }

    fun passValidation(pass: String): Boolean{
        return pass.length >= 8
    }
}