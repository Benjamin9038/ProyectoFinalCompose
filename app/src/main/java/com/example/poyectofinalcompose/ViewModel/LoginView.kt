package com.example.poyectofinalcompose.ViewModel


import androidx.lifecycle.ViewModel

class LoginView : ViewModel() {
    var isLogged = false
        private set

    fun login(password: String): Boolean {
        if (password == "1234") {
            isLogged = true
        }
        return isLogged
    }
}