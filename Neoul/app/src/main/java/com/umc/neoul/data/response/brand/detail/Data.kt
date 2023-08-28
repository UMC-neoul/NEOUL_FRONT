package com.umc.neoul.data.response.brand.detail

import com.umc.neoul.data.model.BrandDetail

data class Data(
    val brandId: Int,
    val brandName: String,
    val categoryVId: List<Int>,
    val categoryVName: List<String>,
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
            com.umc.neoul.data.model.Product(
                deliveryInfo = it.deliveryInfo,
                name = it.name,
                price = it.price,
                productId = it.productId,
                productUrl = it.productUrl,
                productImg = if(it.productImgList.isNotEmpty()){
                    it.productImgList.first()
                }else{
                    ""
                }
            )
        },
        liked = hearted,
        hashTag = categoryVName,
        likeCNT = likeCNT
    )
}