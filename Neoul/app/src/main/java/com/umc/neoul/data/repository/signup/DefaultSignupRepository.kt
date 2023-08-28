package com.umc.neoul.data.repository.signup

import com.umc.neoul.presentation.user.signup.SignupDataFile.SignupInterface
import com.umc.neoul.presentation.user.signup.SignupDataFile.SignupResponse
import com.umc.neoul.presentation.user.signup.SignupDataFile.SignupUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultSignupRepository(
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