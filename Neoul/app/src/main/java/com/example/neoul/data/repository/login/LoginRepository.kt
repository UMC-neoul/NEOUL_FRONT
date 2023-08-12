package com.example.neoul.data.repository.login

import com.example.neoul.presentation.user.login.LoginDataFile.EmailLoginResponse
import com.example.neoul.presentation.user.login.LoginDataFile.User

interface LoginRepository {

    suspend fun login(user: User) :EmailLoginResponse?


}