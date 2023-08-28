package com.umc.neoul.data.network

import com.umc.neoul.data.response.brand.detail.BrandDetailResponse
import com.umc.neoul.data.response.brand.list.BrandResponse
import com.umc.neoul.data.response.like.LikeResponse
import com.umc.neoul.data.response.like.brand.BrandLikeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.Path

interface BrandApiService {

    @GET("/brand/list")
    suspend fun getBrandListApi(
        @Header("Authorization") accessToken: String
    ): Response<BrandResponse>

    @GET("/brand/{brandId}")
    suspend fun getBrandDetailApi(
        @Header("Authorization") accessToken: String,
        @Path("brandId") brandId: Int
    ): Response<BrandDetailResponse>

    @PATCH("/my/brand/like/{brandId}")
    suspend fun likeBrandApi(
        @Header("Authorization") accessToken: String,
        @Path("brandId") brandId: Int
    ): Response<LikeResponse>

    @PATCH("/my/brand/dislike/{brandId}")
    suspend fun dislikeBrandApi(
        @Header("Authorization") accessToken: String,
        @Path("brandId") brandId: Int
    ): Response<LikeResponse>

    @GET("/my/like/brand/list")
    suspend fun likeBrandListApi(
        @Header("Authorization") accessToken: String
    ): Response<BrandLikeResponse>
}