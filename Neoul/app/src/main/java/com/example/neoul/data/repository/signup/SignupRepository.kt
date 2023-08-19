package com.example.neoul.data.repository.signup

import com.example.neoul.presentation.user.signup.SignupDataFile.SignupResponse
import com.example.neoul.presentation.user.signup.SignupDataFile.SignupUser

interface SignupRepository {

    suspend fun signup(signupUser: SignupUser): SignupResponse?



}