package com.example.dummyapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dummyapp.domain.model.User
import com.example.dummyapp.domain.model.UserModel
import com.example.dummyapp.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase): ViewModel(){
    private var _user: MutableStateFlow<User>? = null
    val user: StateFlow<User>? = _user

    private var _state = MutableStateFlow<LoginState>(LoginState.Loading)
    val state: StateFlow<LoginState> = _state

    fun login(userModel: UserModel): User?{
        viewModelScope.launch {
            _state.value = LoginState.Loading
            val result = withContext(Dispatchers.IO) {loginUseCase(userModel)}
            if (result != null){
                _user?.value = result
                _state.value = LoginState.Success(result)
            }
        }
        return user?.value
    }
}