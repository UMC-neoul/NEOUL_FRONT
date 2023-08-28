package com.umc.neoul.presentation.product

import com.umc.neoul.data.model.Product
import com.umc.neoul.presentation.main.brand.detail.BrandDetailState

sealed class ProductState {
    object Uninitialized : ProductState()

    object Failure : ProductState()

    object NotAuth: ProductState()

    data class Success(
        val product: Product
    ) : ProductState()
}