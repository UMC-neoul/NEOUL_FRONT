package com.example.neoul.data.response.like.brand

import com.example.neoul.data.model.BrandItem

data class LikedBrand(
    val brandId: Int,
    val brandName: String,
    val brandIntro: String,
    val brandImg: String,
)

fun likedBrandToBrandItem(likedBrand: LikedBrand): BrandItem {
    return BrandItem(
        id = likedBrand.brandId,
        name = likedBrand.brandName,
        content = likedBrand.brandIntro,
        image = likedBrand.brandImg,
        productList = listOf(),
        liked = true
    )
}


