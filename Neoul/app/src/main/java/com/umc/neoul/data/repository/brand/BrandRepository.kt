package com.umc.neoul.data.repository.brand

import com.umc.neoul.data.response.brand.list.Data

interface BrandRepository {

    suspend fun getBrandList(accessToken: String): List<Data>?

    suspend fun getBrandDetail(
        accessToken: String,
        brandId: Int
    ): com.umc.neoul.data.response.brand.detail.Data?

    suspend fun likeBrand(accessToken: String, brandId: Int)

    suspend fun dislikeBrand(accessToken: String, brandId: Int)

    suspend fun likeBrandList(accessToken: String): com.umc.neoul.data.response.like.brand.Data?
}