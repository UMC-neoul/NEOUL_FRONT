package com.example.neoul.presentation.main.my.Info

import com.google.gson.annotations.SerializedName

data class MyPageInfoResponse(
    @SerializedName("code") val code:Int,
    @SerializedName("message") val message:String,
    @SerializedName("data") val data:MyPageInfoData,
)
