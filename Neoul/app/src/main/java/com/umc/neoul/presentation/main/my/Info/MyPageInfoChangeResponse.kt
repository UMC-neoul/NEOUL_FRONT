package com.umc.neoul.presentation.main.my.Info

import com.umc.neoul.presentation.main.my.data.MyPageData
import com.google.gson.annotations.SerializedName

data class MyPageInfoChangeResponse(
    @SerializedName("code") val code:Int,
    @SerializedName("message") val message : String,
    @SerializedName("data") val data: String,
)
