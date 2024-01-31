package com.example.dummyapp.domain.model.product

import com.google.gson.annotations.SerializedName

data class Category (
    @SerializedName("0"  ) var cat0  : String? = null,
    @SerializedName("1"  ) var cat1  : String? = null,
    @SerializedName("2"  ) var cat2  : String? = null,
    @SerializedName("3"  ) var cat3  : String? = null,
    @SerializedName("4"  ) var cat4  : String? = null,
    @SerializedName("5"  ) var cat5  : String? = null,
    @SerializedName("6"  ) var cat6  : String? = null,
    @SerializedName("7"  ) var cat7  : String? = null,
    @SerializedName("8"  ) var cat8  : String? = null,
    @SerializedName("9"  ) var cat9  : String? = null,
    @SerializedName("10" ) var cat10 : String? = null
)