package com.example.neoul.data.network

import com.example.neoul.data.response.brand.detail.BrandDetailResponse
import com.example.neoul.data.response.brand.list.BrandResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BrandApiService {

    @GET("/brand/list")
    suspend fun getBrandListApi():Response<BrandResponse>

    @GET("/brand/{brandId}")
    suspend fun getBrandDetailApi(
        @Path("brandId") brandId: Int
    ): Response<BrandDetailResponse>
}