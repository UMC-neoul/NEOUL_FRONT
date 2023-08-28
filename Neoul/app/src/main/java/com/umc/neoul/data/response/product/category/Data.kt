package com.umc.neoul.data.response.product.category

import com.umc.neoul.data.model.Product

data class Data(
    val brandName: String,
    val categoryId: Int,
    val createdAt: String,
    val discountedRatio: Int,
    var liked: Boolean,
    val price: Int,
    val productId: Int,
    val productImgList: List<String>,
    val productName: String
)

fun dataToProduct(data: Data): Product {
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

