package com.example.neoul.data.repository.story

import com.example.neoul.data.network.StoryApiService
import com.example.neoul.data.response.story.list.Data
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultStoryRepository(
    private val storyApiService : StoryApiService,
    private val ioDispatcher: CoroutineDispatcher
) : StoryRepository {
    override suspend fun getStoryList() : List<Data>?  = withContext(ioDispatcher) {
        val response = storyApiService.getStoryListApi()
        if (response.isSuccessful){
            response.body()?.data ?: listOf()
        }else{
            listOf()
        }
    }

    override suspend fun getStoryDetail(storyId: Int): com.example.neoul.data.response.story.detail.Data?
    = withContext(ioDispatcher){
        val response = storyApiService.getStoryDetailApi(storyId)
        if (response.isSuccessful){
            response.body()?.data
        }else{
            null
        }
    }
}