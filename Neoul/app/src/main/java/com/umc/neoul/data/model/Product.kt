package com.umc.neoul.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val deliveryInfo: String,
    val name: String,
    val price: Int,
    val productId: Int,
    var productUrl: String,
    val productImg : String,
    var liked : Boolean = false
):Parcelable