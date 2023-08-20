package com.example.neoul.data.response.product.all

import com.example.neoul.data.model.Product

data class Data(
    val brandId: Int,
    val categoryName: String,
    val categoryPId: Int,
    val createdAt: String,
    val deliveryInfo: String,
    val phearted: Boolean,
    val plikeCNT: Any,
    val price: Int,
    val productId: Int,
    val productImgList: List<String>,
    val productName: String,
    val productUrl: String
)

fun dataToProduct(data: Data): Product {
    return Product(
        deliveryInfo = data.deliveryInfo,
        name = data.productName,
        price = data.price,
        productId = data.productId,
        productUrl = data.productUrl,
        productImg = data.productImgList[0],
        liked = data.phearted
    )
}