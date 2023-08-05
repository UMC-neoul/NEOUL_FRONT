package com.example.neoul.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BrandItem(
    val name: String,
    val content: String,
    val image: String,
    val productList: List<Product>,
    var liked : Boolean = false
) : Parcelable
