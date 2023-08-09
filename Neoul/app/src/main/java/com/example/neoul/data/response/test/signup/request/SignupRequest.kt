package com.example.neoul.data.response.test.signup.request

import com.google.gson.annotations.SerializedName

data class SignupRequest(
    @SerializedName("birth")
    val birth: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("username")
    val username: String
)