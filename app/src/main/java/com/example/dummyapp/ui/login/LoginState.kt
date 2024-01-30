package com.example.dummyapp.ui.login

import com.example.dummyapp.domain.model.User

sealed class LoginState {
    data object Loading : LoginState()
    data class Error(val error: String) : LoginState()
    data class Success(val locationModel: User) : LoginState()
}