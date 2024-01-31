package com.example.dummyapp.ui.product

import com.example.dummyapp.domain.model.product.Product
import com.example.dummyapp.domain.model.product.Result

sealed class ProductState {
    data object Loading : ProductState()
    data class Error(val error: String) : ProductState()
    data class Success(val product: Result) : ProductState()
}

sealed class AddProductState {
    data object Loading : AddProductState()
    data class Error(val error: String) : AddProductState()
    data class Success(val product: Product) : AddProductState()
}