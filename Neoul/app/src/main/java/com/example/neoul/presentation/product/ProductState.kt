package com.example.neoul.presentation.product

import com.example.neoul.data.model.Product
import com.example.neoul.presentation.main.brand.detail.BrandDetailState

sealed class ProductState {
    object Uninitialized : ProductState()

    object Failure : ProductState()

    object NotAuth: ProductState()

    data class Success(
        val product: Product
    ) : ProductState()
}