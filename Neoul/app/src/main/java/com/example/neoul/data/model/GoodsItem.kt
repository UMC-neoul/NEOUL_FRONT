package com.example.neoul.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GoodsItem(
    val title: String,
    val percent: Int,
    val price: Int,
    val goodsImage: String,
    var liked: Boolean = false
) : Parcelable
