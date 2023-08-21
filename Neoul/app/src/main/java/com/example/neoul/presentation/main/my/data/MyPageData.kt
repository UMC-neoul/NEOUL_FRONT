package com.example.neoul.presentation.main.my.data

import com.example.neoul.data.model.Product
import com.google.gson.annotations.SerializedName

data class MyPageData(
    @SerializedName("brandName") val brandName:String,
    @SerializedName("clickedAt") val clickedAt:String,
    @SerializedName("price") val price:Int,
    @SerializedName("productId") val productId:Int,
    @SerializedName("productImgList") val productImageList:List<String>,
    @SerializedName("productName") val productName:String,
){

    fun toModel() = MyPageProduct(
        brandName = brandName, clickedAt = clickedAt, price = price, productId = productId, productImgList = productImageList ,productName = productName
    )

}
