package com.umc.neoul.presentation.main.my.Info

import com.google.gson.annotations.SerializedName

data class MyPageInfoData(
    @SerializedName("birth") val birth:String,
    @SerializedName("createdAt") val createdAt:String,
    @SerializedName("firstLogin") val firstLogin:Boolean,
    @SerializedName("imageUrl") val imageUrl:String,
    @SerializedName("name") val name:String,
    @SerializedName("password") val password:String,
    @SerializedName("phone") val phone:String,
    @SerializedName("social") val social:String,
    @SerializedName("updatedAt") val updatedAt:String,
    @SerializedName("userId") val userId:Int,
    @SerializedName("username") val username:String,
)
