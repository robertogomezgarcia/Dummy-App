package com.example.dummyapp.domain.usecase

import com.example.dummyapp.domain.model.Repository
import com.example.dummyapp.domain.model.UserModel
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(userModel: UserModel) = repository.getUser(userModel)
}