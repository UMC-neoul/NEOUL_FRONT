package com.example.neoul.presentation.main.story

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.neoul.data.model.Story
import com.example.neoul.data.network.Url
import com.example.neoul.data.repository.story.StoryRepository
import com.example.neoul.presentation.BaseViewModel
import com.example.neoul.util.getJwt
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
            jwt = "Bearer "+ getJwt()
        }

        val story = storyRepository.getStoryList(jwt)?.map {
            it.toModel()
        } ?: listOf()
        storyLiveData.value = story
    }
}