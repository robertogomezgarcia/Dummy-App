package com.example.dummyapp.domain.usecase

import com.example.dummyapp.domain.model.Repository
import com.example.dummyapp.domain.model.product.Product
import javax.inject.Inject

class SetProductUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(product: Product) = repository.setProduct(product)
}