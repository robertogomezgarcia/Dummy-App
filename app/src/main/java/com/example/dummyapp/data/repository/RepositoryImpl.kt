package com.example.dummyapp.data.repository

import android.util.Log
import com.example.dummyapp.data.network.DummyApiService
import com.example.dummyapp.domain.model.Repository
import com.example.dummyapp.domain.model.login.User
import com.example.dummyapp.domain.model.login.UserModel
import com.example.dummyapp.domain.model.product.Product
import com.example.dummyapp.domain.model.product.Result
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: DummyApiService): Repository {

    override suspend fun getUser(userModel: UserModel): User? {
        kotlin.runCatching { apiService.getUserLogin(userModel) }
            .onSuccess {
                return it.toDomain()
            }
            .onFailure { Log.i("Rober", "Ha ocurrido un error ${it.message}") }

        return null
    }

    override suspend fun getProducts(): Result? {
        kotlin.runCatching { apiService.getProducts() }
            .onSuccess { return it.toDomain() }
            .onFailure {  Log.i("Rober", "Ha ocurrido un error ${it.message}") }

        return null
    }

    override suspend fun getCategories(): List<String>? {
        kotlin.runCatching { apiService.getCategories() }
            .onSuccess { return it }
            .onFailure {  Log.i("Rober", "Ha ocurrido un error ${it.message}") }

        return null
    }

    override suspend fun setProduct(producto: Product): Product? {
        kotlin.runCatching { apiService.setProduct(producto) }
            .onSuccess { return it }
            .onFailure {  Log.i("Rober", "Ha ocurrido un error ${it.message}") }

        return null
    }


}