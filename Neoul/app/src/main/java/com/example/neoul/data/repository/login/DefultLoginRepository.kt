package com.example.neoul.data.repository.login

import android.util.Log
import com.example.neoul.data.preference.ApplicationPreferenceManager
import com.example.neoul.presentation.user.login.LoginDataFile.Data
import com.example.neoul.presentation.user.login.LoginDataFile.EmailLoginResponse
import com.example.neoul.presentation.user.login.LoginDataFile.User
import com.example.neoul.presentation.user.login.LoginDataSource.LoginInterface
import com.example.neoul.util.getJwt
import com.example.neoul.util.saveJwt
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DefultLoginRepository(
    private val loginApiService: LoginInterface,
    private val ioDispatcher: CoroutineDispatcher
) :LoginRepository{
    override suspend fun login(user: User): EmailLoginResponse? = withContext(ioDispatcher) {
        val response = loginApiService.emailLogin(user)

        if(response.isSuccessful){
            Log.d("Tester", "login: ${response.body()}")
            saveJwt(response.body()?.data?.accessToken.toString() )
            return@withContext response.body()

        }else{
            Log.d("Tester", "login: fail ${response}")
            return@withContext response.body()

        }
    }




}