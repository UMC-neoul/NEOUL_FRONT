package com.example.neoul.presentation.user.login.LoginDataFile

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName(value = "email")val email: String,
    @SerializedName(value = "password")val password: String,
)
