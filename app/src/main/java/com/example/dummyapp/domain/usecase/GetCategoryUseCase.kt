package com.example.dummyapp.domain.usecase

import com.example.dummyapp.domain.model.Repository
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke() = repository.getCategories()
}