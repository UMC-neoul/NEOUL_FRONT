package com.umc.neoul.data.response.brand.detail

data class Product(
    val deliveryInfo: String,
    val name: String,
    val price: Int,
    val productId: Int,
    val productUrl: String,
    val productImgList: List<String>,
)