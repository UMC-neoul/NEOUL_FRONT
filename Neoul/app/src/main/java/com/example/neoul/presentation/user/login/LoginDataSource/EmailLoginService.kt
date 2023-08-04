package com.example.neoul.presentation.user.login.LoginDataSource

import com.example.neoul.NeoulApplication

class EmailLoginService {

    private val loginapi = NeoulApplication.sRetrofit.create(LoginInterface::class.java)



}