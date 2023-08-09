package com.example.neoul.data.response.test.login.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)