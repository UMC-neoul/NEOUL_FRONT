package com.umc.neoul.presentation.main.brand.detail

import com.umc.neoul.data.model.BrandDetail

sealed class BrandDetailState {
    object Uninitialized : BrandDetailState()

    object Failure: BrandDetailState()

    object NotAuth: BrandDetailState()

    data class Success(
        val brand: BrandDetail
    ) : BrandDetailState()
}