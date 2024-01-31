package com.example.dummyapp.data.network.response

import com.example.dummyapp.domain.model.product.Category
import com.example.dummyapp.domain.model.product.Result
import com.google.gson.annotations.SerializedName

class CategoryResponse(
    @SerializedName("0"  ) var cat0  : String? = null,
) {
    fun toDomain(){

    }
}