package com.example.neoul.presentation.user.login.LoginDataFile

import com.google.gson.annotations.SerializedName

data class EmailLoginResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: Data?,

)
