package com.example.neoul.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Story(
    val title: String,
    val content: String,
    val author: String,
    val date: String,
    val image : String,
): Parcelable