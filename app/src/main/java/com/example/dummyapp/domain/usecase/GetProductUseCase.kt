package com.example.dummyapp.domain.usecase

import com.example.dummyapp.domain.model.Repository
import com.example.dummyapp.domain.model.login.UserModel
import javax.inject.Inject

class GetProductUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke() = repository.getProducts()
}