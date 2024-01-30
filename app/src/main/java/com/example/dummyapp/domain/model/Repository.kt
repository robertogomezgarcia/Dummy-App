package com.example.dummyapp.domain.model

interface Repository {
    suspend fun getUser(userModel: UserModel): User?
}