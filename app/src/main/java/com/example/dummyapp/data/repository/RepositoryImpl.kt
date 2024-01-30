package com.example.dummyapp.data.repository

import android.util.Log
import com.example.dummyapp.data.network.DummyApiService
import com.example.dummyapp.domain.model.Repository
import com.example.dummyapp.domain.model.User
import com.example.dummyapp.domain.model.UserModel
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
}