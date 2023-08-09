package com.example.neoul.data.repository.product

import com.example.neoul.data.network.ProductApiService
import com.example.neoul.data.response.like.history.request.HistoryRequest
import com.example.neoul.data.response.like.product.Data
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

    override suspend fun likeProductList(accessToken: String): Data?
    = withContext(ioDispatcher){
        val response = productApiService.likeProductListApi(accessToken)
        if (response.isSuccessful){
            response.body()?.data
        }else{
            null
        }
    }

    override suspend fun postViewHistory(accessToken: String, request: HistoryRequest) {
        withContext(ioDispatcher) {
            productApiService.postViewHistory(accessToken, request)
        }
    }
}