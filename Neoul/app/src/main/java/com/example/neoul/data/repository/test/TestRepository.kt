package com.example.neoul.data.repository.test

import com.example.neoul.data.response.test.login.request.LoginRequest
import com.example.neoul.data.response.test.login.response.LoginResponse
import com.example.neoul.data.response.test.signup.request.SignupRequest
import com.example.neoul.data.response.test.signup.response.SignupResponse

interface TestRepository {

    suspend fun login(loginRequest: LoginRequest) : LoginResponse?

    suspend fun signup(signupRequest: SignupRequest) : SignupResponse?
}