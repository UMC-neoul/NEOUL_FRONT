package com.example.neoul.data.response.brand.detail

data class Data(
    val brandId: Int,
    val brandName: String,
    val categoryVId: Int,
    val categoryVName: String,
    val createdAt: String,
    val hashtagList: List<String>,
    val hearted: Boolean,
    val intro: String,
    val likeCNT: Int,
    val productList: List<Product>,
    val profileImg: String
)