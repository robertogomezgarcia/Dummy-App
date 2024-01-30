package com.example.dummyapp.data.network

import com.example.dummyapp.data.network.response.LoginResponse
import com.example.dummyapp.domain.model.UserModel
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface DummyApiService {

    @POST("/auth/login")
    suspend fun getUserLogin( @Body user: UserModel): LoginResponse

}