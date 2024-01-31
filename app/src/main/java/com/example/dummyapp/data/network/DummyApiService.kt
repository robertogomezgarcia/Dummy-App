package com.example.dummyapp.data.network

import com.example.dummyapp.data.network.response.CategoryResponse
import com.example.dummyapp.data.network.response.LoginResponse
import com.example.dummyapp.data.network.response.ProductResponse
import com.example.dummyapp.domain.model.login.UserModel
import com.example.dummyapp.domain.model.product.Product
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DummyApiService {

    @POST("/auth/login")
    suspend fun getUserLogin( @Body user: UserModel): LoginResponse

    @GET("/products?limit=50")
    suspend fun getProducts(): ProductResponse

    @GET("/products/categories")
    suspend fun getCategories(): List<String>

    @POST("/products/add")
    suspend fun setProduct(@Body product: Product): Product
}