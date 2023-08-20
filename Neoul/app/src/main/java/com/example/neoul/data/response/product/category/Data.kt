package com.example.neoul.data.response.product.category

import com.example.neoul.data.model.Product

data class Data(
    val brandName: String,
    val categoryId: Int,
    val createdAt: String,
    val likes: Int,
    val price: Int,
    val productId: Int,
    val productName: String
)

fun dataToProduct(data: Data): Product {
    return Product(
        deliveryInfo = "",
        name = data.productName,
        price = data.price,
        productId = data.productId,
        productUrl = "",
        productImg = "",
        liked = false
    )
}

