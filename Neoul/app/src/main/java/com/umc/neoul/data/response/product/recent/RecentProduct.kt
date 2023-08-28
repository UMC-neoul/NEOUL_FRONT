package com.umc.neoul.data.response.product.recent

data class RecentProduct(
    val code: Int,
    val data: List<Data>,
    val message: String
)