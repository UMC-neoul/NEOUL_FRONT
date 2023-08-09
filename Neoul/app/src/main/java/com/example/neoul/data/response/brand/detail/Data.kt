package com.example.neoul.data.response.brand.detail

import com.example.neoul.data.model.BrandDetail

data class Data(
    val brandId: Int,
    val brandName: String,
    val categoryVId: Int,
    val categoryVName: String,
    val createdAt: String,
    val hashtagList: List<String>,
    val hearted: Boolean,
    val intro: String,
    val likeCNT: Int,
    val productList: List<Product>,
    val profileImg: String
){
    fun toModel() = BrandDetail(
        id = brandId,
        name = brandName,
        content = intro,
        image = profileImg,
        productList = productList.map {
            com.example.neoul.data.model.Product(
                deliveryInfo = it.deliveryInfo,
                name = it.name,
                price = it.price,
                productId = it.productId,
                productUrl = it.productUrl
            )
        },
        liked = hearted,
        hashTag = hashtagList,
        likeCNT = likeCNT
    )
}