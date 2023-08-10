package com.example.neoul.data.repository.brand

import com.example.neoul.data.response.brand.list.Data

interface BrandRepository {

    suspend fun getBrandList(): List<Data>?

    suspend fun getBrandDetail(brandId: Int): com.example.neoul.data.response.brand.detail.Data?
}