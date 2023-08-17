package com.example.neoul.data.response.product.all

data class Data(
    val brandId: Int,
    val categoryName: String,
    val categoryPId: Int,
    val createdAt: String,
    val deliveryInfo: String,
    val phearted: Any,
    val plikeCNT: Any,
    val price: Int,
    val productId: Int,
    val productImgList: List<String>,
    val productName: String,
    val productUrl: String
)