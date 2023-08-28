package com.umc.neoul.data.repository.signup

import com.umc.neoul.presentation.user.signup.SignupDataFile.SignupResponse
import com.umc.neoul.presentation.user.signup.SignupDataFile.SignupUser

interface SignupRepository {

    suspend fun signup(signupUser: SignupUser): SignupResponse?



}