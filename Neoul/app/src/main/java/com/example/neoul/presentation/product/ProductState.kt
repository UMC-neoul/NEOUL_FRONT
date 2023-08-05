package com.example.neoul.presentation.product

import com.example.neoul.data.model.GoodsItem
import com.example.neoul.data.model.Product

sealed class ProductState {
    object Uninitialized : ProductState()

    data class Success(
        val product: Product
    ) : ProductState()
}