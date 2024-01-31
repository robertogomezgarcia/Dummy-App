package com.example.dummyapp.domain.model

import com.example.dummyapp.domain.model.login.User
import com.example.dummyapp.domain.model.product.Result
import com.example.dummyapp.domain.model.login.UserModel
import com.example.dummyapp.domain.model.product.Product

interface Repository {
    suspend fun getUser(userModel: UserModel): User?
    suspend fun getProducts():Result?

    suspend fun getCategories():List<String>?

    suspend fun setProduct(products: Product):Product?
}