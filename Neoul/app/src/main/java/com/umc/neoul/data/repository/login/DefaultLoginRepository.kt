package com.umc.neoul.data.repository.login

import android.util.Log
import com.umc.neoul.presentation.user.login.LoginDataFile.EmailLoginResponse
import com.umc.neoul.presentation.user.login.LoginDataFile.User
import com.umc.neoul.presentation.user.login.LoginDataSource.LoginInterface
import com.umc.neoul.util.saveJwt
import com.umc.neoul.util.saveUsername
import com.umc.neoul.util.savekakaoLogin
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultLoginRepository(
    private val loginApiService: LoginInterface,
    private val ioDispatcher: CoroutineDispatcher
) :LoginRepository{
    override suspend fun login(user: User): EmailLoginResponse? = withContext(ioDispatcher) {
        val response = loginApiService.emailLogin(user)

        if(response.isSuccessful){
            Log.d("Tester", "login: ${response.body()}")
            saveJwt( "Bearer " + response.body()?.data?.accessToken.toString())
            savekakaoLogin(false)
            saveUsername(response.body()?.data?.username.toString())
            Log.d("Tester", "login: ${response.body()!!.data!!.firstLogin}")

            return@withContext response.body()

        }else{
            return@withContext null

        }
    }




}