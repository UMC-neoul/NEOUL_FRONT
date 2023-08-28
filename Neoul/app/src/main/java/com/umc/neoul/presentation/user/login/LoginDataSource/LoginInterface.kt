package com.umc.neoul.presentation.user.login.LoginDataSource

import com.umc.neoul.presentation.user.login.LoginDataFile.EmailLoginResponse
import com.umc.neoul.presentation.user.login.LoginDataFile.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginInterface {

    @POST("/user/login")
    suspend fun emailLogin(@Body user: User): Response<EmailLoginResponse>


}