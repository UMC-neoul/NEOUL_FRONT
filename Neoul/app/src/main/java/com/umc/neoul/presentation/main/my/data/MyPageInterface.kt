package com.umc.neoul.presentation.main.my.data

import com.umc.neoul.presentation.main.my.Info.MyPageInfoChangeResponse
import com.umc.neoul.presentation.main.my.Info.MyPageInfoResponse
import com.umc.neoul.presentation.main.my.Info.UserInfoData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH

interface MyPageInterface {

    @GET("/my/click/product/list")
    suspend fun myPageList(@Header("Authorization") accessToken: String):Response<MyPageResponse>

    @GET("/user/now")
    suspend fun myPageUser(@Header("Authorization") accessToken: String):Response<MyPageInfoResponse>

    @PATCH("/user/edit")
    suspend fun myPageUserEdit(
        @Header("Authorization") accessToken: String,
        @Body userinfodata : UserInfoData
    ):Response<MyPageInfoChangeResponse>
}