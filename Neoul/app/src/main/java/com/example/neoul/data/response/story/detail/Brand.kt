package com.example.neoul.data.response.story.detail

import com.example.neoul.data.model.BrandItem

data class Brand(
    val brandId: Int,
    val categoryVId: Int,
    val categoryVName: String,
    val intro: String,
    val name: String,
    val products: List<Product>,
    val profileImg: String
){
    fun toModel() =BrandItem(
        name = name,
        content = intro,
        image = profileImg,
        productList = products.map {
            com.example.neoul.data.model.Product(
                deliveryInfo = it.deliveryInfo,
                name = it.name,
                price = it.price,
                productId = it.productId,
                productUrl = it.productUrl
            )
        }
    )
}