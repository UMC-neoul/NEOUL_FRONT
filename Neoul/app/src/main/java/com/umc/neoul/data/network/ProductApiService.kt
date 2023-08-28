package com.umc.neoul.data.network

import com.umc.neoul.data.response.like.LikeResponse
import com.umc.neoul.data.response.like.history.request.HistoryRequest
import com.umc.neoul.data.response.like.history.response.HistoryResponse
import com.umc.neoul.data.response.like.product.ProductLikeResponse
import com.umc.neoul.data.response.product.all.AllProduct
import com.umc.neoul.data.response.product.category.CategoryProduct
import com.umc.neoul.data.response.product.detail.ProductDetailResponse
import com.umc.neoul.data.response.product.recent.RecentProduct
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApiService {

    @PATCH("/my/product/like/{productId}")
    suspend fun likeProductApi(
        @Header("Authorization") accessToken: String,
        @Path("productId") productId: Int
    ): Response<LikeResponse>

    @PATCH("/my/product/dislike/{productId}")
    suspend fun dislikeProductApi(
        @Header("Authorization") accessToken: String,
        @Path("productId") productId: Int
    ): Response<LikeResponse>

    @GET("/my/like/product/list")
    suspend fun likeProductListApi(
        @Header("Authorization") accessToken: String
    ): Response<ProductLikeResponse>

     @POST("/my/click/product")
    suspend fun postViewHistory(
         @Header("Authorization") accessToken: String,
         @Body request: HistoryRequest
    ): Response<HistoryResponse>

    @GET("/product/list")
    suspend fun allProductApi(
        @Header("Authorization") accessToken: String
    ): Response<AllProduct>

    @GET("/product/category/{categoryId}")
    suspend fun categoryProductApi(
        @Header("Authorization") accessToken: String,
        @Path("categoryId") categoryId: Int,
        @Query("option") option: Int
    ): Response<CategoryProduct>

    @GET("/my/click/product/list")
    suspend fun recentProductListApi(
        @Header("Authorization") accessToken: String
    ): Response<RecentProduct>


    @GET("/product/{productId}")
    suspend fun getProductDetailApi(
        @Header("Authorization") accessToken: String,
        @Path("productId") productId: Int
    ): Response<ProductDetailResponse>
}