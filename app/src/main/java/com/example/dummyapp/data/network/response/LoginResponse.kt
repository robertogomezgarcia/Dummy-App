package com.example.dummyapp.data.network.response

import com.example.dummyapp.domain.model.login.User
import com.google.gson.annotations.SerializedName

class LoginResponse(
    @SerializedName("id"        ) var id        : Int?    = null,
    @SerializedName("username"  ) var username  : String? = null,
    @SerializedName("email"     ) var email     : String? = null,
    @SerializedName("firstName" ) var firstName : String? = null,
    @SerializedName("lastName"  ) var lastName  : String? = null,
    @SerializedName("gender"    ) var gender    : String? = null,
    @SerializedName("image"     ) var image     : String? = null,
    @SerializedName("token"     ) var token     : String? = null) {
    fun toDomain(): User {
        return User(
            id,
            username,
            email,
            firstName,
            lastName,
            gender,
            image,
            token
        )
    }
}