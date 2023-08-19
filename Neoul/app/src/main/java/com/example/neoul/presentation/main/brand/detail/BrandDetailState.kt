package com.example.neoul.presentation.main.brand.detail

import com.example.neoul.data.model.BrandDetail

sealed class BrandDetailState {
    object Uninitialized : BrandDetailState()

    object Failure: BrandDetailState()

    object NotAuth: BrandDetailState()

    data class Success(
        val brand: BrandDetail
    ) : BrandDetailState()
}