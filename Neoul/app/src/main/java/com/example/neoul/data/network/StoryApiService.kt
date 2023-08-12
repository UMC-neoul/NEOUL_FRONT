package com.example.neoul.data.network

import com.example.neoul.data.response.story.detail.StoryDetailResponse
import com.example.neoul.data.response.story.list.StoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface StoryApiService {

    @GET("/story/list")
    suspend fun getStoryListApi(
        @Header("Authorization") accessToken: String
    ): Response<StoryResponse>

    @GET("/story/{storyId}")
    suspend fun getStoryDetailApi(
        @Header("Authorization") accessToken: String,
        @Path("storyId") storyId: Int
    ): Response<StoryDetailResponse>
}