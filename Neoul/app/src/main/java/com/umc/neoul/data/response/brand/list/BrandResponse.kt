package com.umc.neoul.data.response.brand.list

data class BrandResponse(
    val code: Int,
    val data: List<Data>,
    val message: String
)