package com.umc.neoul.data.response.story.detail

data class Product(
    val deliveryInfo: String,
    val name: String,
    val price: Int,
    val productId: Int,
    val productUrl: String,
    val productImgList: List<String>
)