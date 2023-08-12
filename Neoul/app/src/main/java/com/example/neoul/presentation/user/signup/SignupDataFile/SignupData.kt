package com.example.neoul.presentation.user.signup.SignupDataFile

import com.google.gson.annotations.SerializedName

data class SignupData(
    @SerializedName("firstLogin") val firstLogin: Boolean,
    @SerializedName("imageUrl") val imageUrl:String,
    @SerializedName("name") val name: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("social") val social: String,
    @SerializedName("username") val username: String,
)
