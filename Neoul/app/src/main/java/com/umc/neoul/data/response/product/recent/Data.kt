package com.umc.neoul.data.response.product.recent

import com.umc.neoul.R
import com.umc.neoul.data.model.Product

data class Data(
    val brandName: String,
    val clickedAt: String,
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
        liked = false
    )
}