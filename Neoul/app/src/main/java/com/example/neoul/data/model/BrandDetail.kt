package com.example.neoul.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BrandDetail(
    val id : Int,
    val name: String,
    val content: String,
    val image: String,
    val productList: List<Product>,
    var liked: Boolean ,
    val hashTag: List<String>?,
    val likeCNT: Int
):Parcelable