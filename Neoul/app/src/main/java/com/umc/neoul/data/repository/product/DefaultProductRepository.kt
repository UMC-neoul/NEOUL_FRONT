package com.umc.neoul.data.repository.product

import com.umc.neoul.data.network.ProductApiService
import com.umc.neoul.data.response.like.history.request.HistoryRequest
import com.umc.neoul.data.response.like.product.Data
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultProductRepository(
    private val productApiService: ProductApiService,
    private val ioDispatcher: CoroutineDispatcher
) : ProductRepository {
    override suspend fun likeProduct(accessToken: String, productId: Int) {
        withContext(ioDispatcher) {
            productApiService.likeProductApi(accessToken, productId)
        }
    }

    override suspend fun dislikeProduct(accessToken: String, productId: Int) {
        withContext(ioDispatcher) {
            productApiService.dislikeProductApi(accessToken, productId)
        }
    }

    override suspend fun likeProductList(accessToken: String): Data? = withContext(ioDispatcher) {
        val response = productApiService.likeProductListApi(accessToken)
        if (response.isSuccessful) {
            response.body()?.data
        } else {
            null
        }
    }

    override suspend fun postViewHistory(accessToken: String, request: HistoryRequest) {
        withContext(ioDispatcher) {
            productApiService.postViewHistory(accessToken, request)
        }
    }

    override suspend fun allProduct(accessToken: String): List<com.umc.neoul.data.response.product.all.Data>? =
        withContext(ioDispatcher) {
            val response = productApiService.allProductApi(accessToken)
            if (response.isSuccessful) {
                response.body()?.data
            } else {
                null
            }

        }

    override suspend fun categoryProduct(
        accessToken: String,
        categoryId: Int,
        option: Int
    ): List<com.umc.neoul.data.response.product.category.Data>? =
        withContext(ioDispatcher) {
            val response = productApiService.categoryProductApi(accessToken, categoryId, option)
            if (response.isSuccessful) {
                response.body()?.data
            } else {
                null
            }
        }

    override suspend fun recentProductList(accessToken: String): List<com.umc.neoul.data.response.product.recent.Data>? =
        withContext(ioDispatcher) {
            val response = productApiService.recentProductListApi(accessToken)
            if (response.isSuccessful) {
                response.body()?.data
            } else {
                null
            }
        }
        
    override suspend fun getProductDetail(accessToken: String, productId: Int): String? = withContext(ioDispatcher) {
        val response = productApiService.getProductDetailApi(accessToken, productId)
        if (response.isSuccessful){
            response.body()?.data?.productUrl
        } else{
            null
        }
    }


}