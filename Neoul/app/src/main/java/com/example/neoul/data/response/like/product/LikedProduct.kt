package com.example.neoul.data.response.like.product

import com.example.neoul.data.model.Product


data class LikedProduct(
    val productId: Int,
    val productName: String
)

fun dataToProduct(data: LikedProduct): Product {
    return Product(
        deliveryInfo = "",
        name = data.productName,
        price = 0,
        productId = data.productId,
        productUrl = "",
        productImg = "",
        liked = false
    )
}