package com.example.neoul.data.repository.test

import com.example.neoul.data.network.StoryApiService
import com.example.neoul.data.network.TestApiService
import com.example.neoul.data.response.test.login.request.LoginRequest
import com.example.neoul.data.response.test.login.response.LoginResponse
import com.example.neoul.data.response.test.signup.request.SignupRequest
import com.example.neoul.data.response.test.signup.response.SignupResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultTestRepository(
    private val testApiService: TestApiService ,
    private val ioDispatcher: CoroutineDispatcher
) : TestRepository {
    override suspend fun login(loginRequest: LoginRequest): LoginResponse? = withContext(ioDispatcher) {
        val response = testApiService.loginApi(loginRequest)
        if (response.isSuccessful){
            response.body()
        }else{
            null
        }
    }

    override suspend fun signup(signupRequest: SignupRequest) : SignupResponse? = withContext( ioDispatcher ) {
        val response = testApiService.signupApi(signupRequest)
        if (response.isSuccessful){
            response.body()
        }else{
            null
        }
    }
}