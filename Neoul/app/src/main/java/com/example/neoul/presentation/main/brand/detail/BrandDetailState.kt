package com.example.neoul.presentation.main.brand.detail

import com.example.neoul.data.model.BrandItem

sealed class BrandDetailState {
    object Uninitialized : BrandDetailState()

    data class Success(
        val product: BrandItem
    ) : BrandDetailState()
}