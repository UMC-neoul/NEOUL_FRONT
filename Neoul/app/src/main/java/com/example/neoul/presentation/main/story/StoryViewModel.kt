package com.example.neoul.presentation.main.story

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.neoul.data.model.Story
import com.example.neoul.presentation.BaseViewModel
import kotlinx.coroutines.launch

class StoryViewModel : BaseViewModel() {

    val storyLiveData = MutableLiveData<List<Story>>()
    override fun fetchData() = viewModelScope.launch {
        //데스트용 더미데이터
        val  storyList = listOf(
            Story("aaaa","aaaa\naa\naa\naa\naa\n\n\n\n\n\n\n\n\naa","aaaaa","aaa","aa"),
            Story("bbbb","bbbb","bbbb","bbb","aa"),
            Story("cccc","cccc","bbbb","bbb","aa"),
            Story("bbbb","bbbb","bbbb","bbb","aa"),
            Story("eeee","bbbb","bbbb","bbb","aa")
        )
        storyLiveData.value =storyList
    }
}