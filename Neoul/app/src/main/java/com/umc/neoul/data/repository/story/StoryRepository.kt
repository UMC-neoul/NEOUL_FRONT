package com.umc.neoul.data.repository.story

import com.umc.neoul.data.response.story.list.Data

interface StoryRepository {

    suspend fun getStoryList(accessToken: String): List<Data>?

    suspend fun getStoryDetail(
        accessToken: String,
        storyId: Int
    ): com.umc.neoul.data.response.story.detail.Data?
}