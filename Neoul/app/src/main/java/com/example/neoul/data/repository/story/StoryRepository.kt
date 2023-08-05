package com.example.neoul.data.repository.story

import com.example.neoul.data.response.story.list.Data

interface StoryRepository {

    suspend fun getStoryList(): List<Data>?

    suspend fun getStoryDetail(
        storyId: Int
    ): com.example.neoul.data.response.story.detail.Data?
}