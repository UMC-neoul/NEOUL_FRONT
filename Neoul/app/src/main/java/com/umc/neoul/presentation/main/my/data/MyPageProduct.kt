package com.umc.neoul.presentation.main.my.data

data class MyPageProduct(
    val brandName: String,
    val price: Int,
    val clickedAt: String,
    val productId: Int,
    val productImgList: List<String>,
    val productName: String,
)
