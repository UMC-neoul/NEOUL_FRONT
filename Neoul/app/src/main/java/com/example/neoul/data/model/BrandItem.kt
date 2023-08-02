package com.example.neoul.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BrandItem(
    val name: String,
    val content: String,
    val image: String,
    val data: String,
    val productList: List<GoodsItem>,
    var liked : Boolean = false
) : Parcelable
