package com.umc.neoul.presentation.main.my.Info

import com.google.gson.annotations.SerializedName

data class UserInfoData(
    @SerializedName("birth") val birth: String,
    @SerializedName("name") val name: String,
    @SerializedName("phone") val phone: String,
    )
