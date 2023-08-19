package com.example.neoul.presentation.main.my.Info

import com.example.neoul.presentation.main.my.data.MyPageData
import com.google.gson.annotations.SerializedName

data class MyPageInfoChangeResponse(
    @SerializedName("code") val code:Int,
    @SerializedName("message") val message : String,
    @SerializedName("data") val data: String,
)
