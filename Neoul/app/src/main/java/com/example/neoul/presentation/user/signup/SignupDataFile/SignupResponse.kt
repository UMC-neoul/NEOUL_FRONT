package com.example.neoul.presentation.user.signup.SignupDataFile

import android.service.autofill.UserData
import com.google.gson.annotations.SerializedName

data class SignupResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: SignupData,
)
