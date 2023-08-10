package com.example.neoul.data.network

import com.example.neoul.data.response.story.detail.StoryDetailResponse
import com.example.neoul.data.response.story.list.StoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StoryApiService {

    @GET("/story/list")
    suspend fun getStoryListApi(): Response<StoryResponse>

    @GET("/story/{storyId}")
    suspend fun getStoryDetailApi(
        @Path("storyId") storyId : Int
    ): Response<StoryDetailResponse>
}