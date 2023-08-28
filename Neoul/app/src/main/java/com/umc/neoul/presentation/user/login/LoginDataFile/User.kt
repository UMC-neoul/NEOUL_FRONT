package com.umc.neoul.presentation.user.login.LoginDataFile

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName(value = "username")val username: String,
    @SerializedName(value = "password")val password: String,
)
