package com.example.neoul.data.repository.signup

import com.example.neoul.presentation.user.signup.SignupDataFile.SignupInterface
import com.example.neoul.presentation.user.signup.SignupDataFile.SignupResponse
import com.example.neoul.presentation.user.signup.SignupDataFile.SignupUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefultSignupRepository(
    private val signupApiService:SignupInterface,
    private val ioDispatcher: CoroutineDispatcher,

):SignupRepository {
    override suspend fun signup(signupUser: SignupUser): SignupResponse? = withContext(ioDispatcher) {
        val response = signupApiService.signUp(signupUser)
        if(response.isSuccessful){
            return@withContext response.body()
        }else{
            return@withContext null
        }
    }
}