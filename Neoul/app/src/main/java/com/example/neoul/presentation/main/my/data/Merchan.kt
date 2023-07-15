package com.example.neoul.presentation.main.my.data
import com.google.gson.annotations.SerializedName


data class Merchan(
    @SerializedName("ware") val ware:String,
    @SerializedName("price") val price:Int,
    @SerializedName("salePercent") val salePercent:String,
    @SerializedName("wareImageUrl") val wareImageUrl:String
)
