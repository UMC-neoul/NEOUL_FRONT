package com.example.neoul.data.repository.mypage

import com.example.neoul.presentation.main.my.Info.MyPageInfoChangeResponse
import com.example.neoul.presentation.main.my.Info.MyPageInfoResponse
import com.example.neoul.presentation.main.my.Info.UserInfoData
import com.example.neoul.presentation.main.my.data.MyPageResponse

interface MyPageRepository {


    suspend fun mypageproduct(accessToken:String): MyPageResponse?

    suspend fun mypageinfo(accessToken: String): MyPageInfoResponse?

    suspend fun mypageinfochange(accessToken: String,userInfoData: UserInfoData): MyPageInfoChangeResponse?

}