package com.umc.neoul.presentation.user.login.LoginDataFile

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("firstLogin") val firstLogin: Boolean,
    @SerializedName("refreshToken") val refreshJwt: String,
    @SerializedName("username") val username: String,

)
