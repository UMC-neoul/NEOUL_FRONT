package com.example.neoul.data.repository.mypage

import android.util.Log
import com.example.neoul.presentation.main.my.Info.MyPageInfoChangeResponse
import com.example.neoul.presentation.main.my.Info.MyPageInfoResponse
import com.example.neoul.presentation.main.my.Info.UserInfoData
import com.example.neoul.presentation.main.my.data.MyPageInterface
import com.example.neoul.presentation.main.my.data.MyPageResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultMyPageRepository(
    private val myPageApiService : MyPageInterface,
    private val ioDispatcher: CoroutineDispatcher,
):MyPageRepository {

    override suspend fun mypageproduct(accessToken:String):MyPageResponse? = withContext(ioDispatcher){
        val response = myPageApiService.myPageList(accessToken)
        Log.d("Tester", "mypageproduct: ${response.body()}")
        if(response.isSuccessful){
            return@withContext response.body()
        }else{
            return@withContext  response.body()
        }

    }

    override suspend fun mypageinfo(accessToken: String): MyPageInfoResponse?  = withContext(ioDispatcher){

        val response = myPageApiService.myPageUser(accessToken)
        Log.d("Tester", "mypageinfo: dd${response}")
        if(response.isSuccessful){
            Log.d("Tester", "mypageinfo: ${response.body()}")
            return@withContext response.body()
        }else{
            Log.d("Tester", "mypageinfo: fail ${response.body()}")
            return@withContext response.body()
        }
    }

    override suspend fun mypageinfochange(accessToken: String ,userInfoData: UserInfoData): MyPageInfoChangeResponse? = withContext(ioDispatcher){
        val response = myPageApiService.myPageUserEdit(accessToken,userInfoData)

        if(response.isSuccessful){
            return@withContext response.body()
        }else{
            return@withContext response.body()
        }
    }


}