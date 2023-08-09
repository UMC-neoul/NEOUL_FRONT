package com.example.neoul.data.repository.product

import com.example.neoul.data.response.like.history.request.HistoryRequest
import com.example.neoul.data.response.like.product.Data

interface ProductRepository {

    suspend fun likeProduct(
        accessToken: String,
        productId: Int
    )

    suspend fun dislikeProduct(
        accessToken: String,
        productId: Int
    )

    suspend fun likeProductList(
        accessToken: String
    ) : Data?

    suspend fun postViewHistory(
        accessToken: String,
        request: HistoryRequest
    )
}