package com.umc.neoul.data.repository.story

import com.umc.neoul.data.network.StoryApiService
import com.umc.neoul.data.response.story.list.Data
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultStoryRepository(
    private val storyApiService : StoryApiService,
    private val ioDispatcher: CoroutineDispatcher
) : StoryRepository {
    override suspend fun getStoryList(accessToken: String) : List<Data>?  = withContext(ioDispatcher) {
        val response = storyApiService.getStoryListApi(accessToken)
        if (response.isSuccessful){
            response.body()?.data ?: listOf()
        }else{
            listOf()
        }
    }

    override suspend fun getStoryDetail(accessToken: String,storyId: Int): com.umc.neoul.data.response.story.detail.Data?
    = withContext(ioDispatcher){
        val response = storyApiService.getStoryDetailApi(accessToken,storyId)
        if (response.isSuccessful){
            response.body()?.data
        }else{
            null
        }
    }
}