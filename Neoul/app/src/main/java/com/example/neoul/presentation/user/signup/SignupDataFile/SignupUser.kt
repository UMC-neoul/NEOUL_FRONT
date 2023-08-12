package com.example.neoul.presentation.user.signup.SignupDataFile

import com.google.gson.annotations.SerializedName

data class SignupUser(
    @SerializedName("birth") val birth: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("name") val name: String,
    @SerializedName("password") val password: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("username") val username: String,
)
