package com.umc.neoul.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Story(
    val id: Int,
    val title: String,
    val category: String,
    val date: String,
    val image : String,
): Parcelable