package com.umc.neoul.data.response.like.product

import com.umc.neoul.data.model.Product


data class LikedProduct(
    val brandId: Int,
    val brandName: String,
    val discountedRatio: Int,
    val discountedSalePrice: Int,
    val price: Int,
    val productId: Int,
    val productImgList: List<String>,
    val productName: String,
    var liked: Boolean = true
)

fun dataToProduct(data: LikedProduct): Product {
    return Product(
        deliveryInfo = "",
        name = data.productName,
        price = data.price,
        productId = data.productId,
        productUrl = "",
        productImg = data.productImgList.firstOrNull() ?: "",
        liked = data.liked
    )
}