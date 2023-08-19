package com.example.neoul.presentation.main.my.data

import com.google.gson.annotations.SerializedName

data class MyPageResponse (
    @SerializedName("code") val code:Int,
    @SerializedName("message") val message : String,
    @SerializedName("data") val data: List<MyPageData>,


)