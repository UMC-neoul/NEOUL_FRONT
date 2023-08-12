package com.example.neoul.presentation.product

import com.example.neoul.data.model.Product

sealed class ProductState {
    object Uninitialized : ProductState()

    object Failure : ProductState()

    data class Success(
        val product: Product
    ) : ProductState()
}