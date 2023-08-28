package com.umc.neoul.presentation.main.story

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.umc.neoul.data.model.Story
import com.umc.neoul.data.network.Url
import com.umc.neoul.data.repository.story.StoryRepository
import com.umc.neoul.presentation.BaseViewModel
import com.umc.neoul.util.getJwt
import kotlinx.coroutines.launch

class StoryViewModel(
    private val storyRepository: StoryRepository
) : BaseViewModel() {

    private var jwt =""

    val storyLiveData = MutableLiveData<List<Story>>()
    override fun fetchData() = viewModelScope.launch {

        //accessToken 가져오기 (비 회원도 일단 가져오기)
        if (getJwt().isNullOrEmpty()){
            jwt = Url.AUTH_KEY
        }else{
            jwt = getJwt().toString()
        }

        val story = storyRepository.getStoryList(jwt)?.map {
            it.toModel()
        }?.reversed() ?: listOf()
        storyLiveData.value = story
    }
}