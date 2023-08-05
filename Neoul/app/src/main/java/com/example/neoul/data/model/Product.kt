package com.example.neoul.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val deliveryInfo: String,
    val name: String,
    val price: Int,
    val productId: Int,
    val productUrl: String,
    var liked : Boolean = false
):Parcelable