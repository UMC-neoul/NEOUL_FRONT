package com.umc.neoul.data.repository.login

import com.umc.neoul.presentation.user.login.LoginDataFile.EmailLoginResponse
import com.umc.neoul.presentation.user.login.LoginDataFile.User

interface LoginRepository {

    suspend fun login(user: User) :EmailLoginResponse?


}