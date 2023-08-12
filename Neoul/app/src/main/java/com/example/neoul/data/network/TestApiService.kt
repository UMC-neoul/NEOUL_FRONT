package com.example.neoul.data.network

import com.example.neoul.data.response.test.login.request.LoginRequest
import com.example.neoul.data.response.test.login.response.LoginResponse
import com.example.neoul.data.response.test.signup.request.SignupRequest
import com.example.neoul.data.response.test.signup.response.SignupResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TestApiService {

    @POST("/user/signup")
    suspend fun signupApi(@Body request: SignupRequest) : Response<SignupResponse>

    @POST("/user/login")
    suspend fun loginApi(@Body request : LoginRequest) : Response<LoginResponse>

}