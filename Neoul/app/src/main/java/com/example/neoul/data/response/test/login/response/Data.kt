package com.example.neoul.data.response.test.login.response

data class Data(
    val accessToken: String,
    val firstLogin: Boolean,
    val refreshToken: String,
    val username: String
)