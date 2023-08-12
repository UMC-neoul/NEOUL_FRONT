package com.example.neoul.data.response.like.product

data class Data(
    val likedProduct: List<LikedProduct>,
    val productCnt: Int,
    val userId: Int
)