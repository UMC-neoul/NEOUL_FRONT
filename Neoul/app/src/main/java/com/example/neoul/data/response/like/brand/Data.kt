package com.example.neoul.data.response.like.brand

data class Data(
    val brandCnt: Int,
    val likedBrands: List<LikedBrand>,
    val userId: Int
)