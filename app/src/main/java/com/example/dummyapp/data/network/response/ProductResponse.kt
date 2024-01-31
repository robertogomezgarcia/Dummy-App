package com.example.dummyapp.data.network.response

import com.example.dummyapp.domain.model.product.Product
import com.example.dummyapp.domain.model.product.Result
import com.google.gson.annotations.SerializedName

class ProductResponse(
    @SerializedName("products") var products: ArrayList<Product> = arrayListOf(),
    @SerializedName("total") var total: Int? = null,
    @SerializedName("skip") var skip: Int? = null,
    @SerializedName("limit") var limit: Int? = null
){
    fun toDomain(): Result {
        return Result(products, total, skip, limit)
    }
}
