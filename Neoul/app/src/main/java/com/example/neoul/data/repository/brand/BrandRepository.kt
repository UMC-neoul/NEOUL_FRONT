package com.example.neoul.data.repository.brand

import com.example.neoul.data.response.brand.list.Data

interface BrandRepository {

    suspend fun getBrandList(accessToken: String): List<Data>?

    suspend fun getBrandDetail(
        accessToken: String,
        brandId: Int
    ): com.example.neoul.data.response.brand.detail.Data?

    suspend fun likeBrand(accessToken: String, brandId: Int)

    suspend fun dislikeBrand(accessToken: String, brandId: Int)

    suspend fun likeBrandList(accessToken: String): com.example.neoul.data.response.like.brand.Data?
}