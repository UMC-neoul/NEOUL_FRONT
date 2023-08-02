package com.example.neoul.presentation.product

import com.example.neoul.data.model.GoodsItem

sealed class ProductState {
    object Uninitialized : ProductState()

    data class Success(
        val product: GoodsItem
    ) : ProductState()
}