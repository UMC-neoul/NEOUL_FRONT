package com.example.neoul.presentation.user.signup.SignupDataFile

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignupInterface {

    @POST("/user/signup")
    suspend fun signUp(@Body signupUser: SignupUser) : Response<SignupResponse>

}