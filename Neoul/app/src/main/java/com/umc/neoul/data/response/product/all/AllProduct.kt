package com.umc.neoul.data.response.product.all

data class AllProduct(
    val code: Int,
    val data: List<Data>,
    val message: String
)