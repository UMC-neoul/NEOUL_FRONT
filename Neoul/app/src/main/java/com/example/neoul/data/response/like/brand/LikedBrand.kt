package com.example.neoul.data.response.like.brand

import com.example.neoul.data.model.BrandItem

data class LikedBrand(
    val brandId: Int,
    val brandName: String
)

fun likedBrandToBrandItem(likedBrand: LikedBrand): BrandItem {
    return BrandItem(
        id = likedBrand.brandId,
        name = likedBrand.brandName,
        content = "",
        image = "",
        productList = listOf(),
        liked = false
    )
}


