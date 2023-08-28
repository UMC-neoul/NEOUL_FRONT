package com.umc.neoul.data.response.product.detail

data class Data(
    val brandId: Int,
    val categoryName: String,
    val categoryPId: Int,
    val createdAt: String,
    val deliveryInfo: String,
    val discountedRatio: Int,
    val discountedSalePrice: Int,
    val phearted: Boolean,
    val plikeCNT: Int,
    val price: Int,
    val productId: Int,
    val productImgList: List<String>,
    val productName: String,
    val productUrl: String
)