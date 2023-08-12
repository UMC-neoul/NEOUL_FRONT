package com.example.neoul.presentation.main.story.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.neoul.data.model.Story
import com.example.neoul.data.network.Url
import com.example.neoul.data.repository.story.StoryRepository
import com.example.neoul.presentation.BaseViewModel
import kotlinx.coroutines.launch

class StoryDetailViewModel(
    private val story: Story,
    private val storyRepository: StoryRepository
) : BaseViewModel() {

    val storyDetailLiveData = MutableLiveData<StoryDetailState>(StoryDetailState.Uninitialized)

    override fun fetchData() = viewModelScope.launch {

        storyDetailLiveData.value = StoryDetailState.Loading

        //STORY DETAIL GET
        storyRepository.getStoryDetail(Url.AUTH_KEY, story.id)?.let {
            storyDetailLiveData.value = StoryDetailState.Success(
                story = story,
                content = it.content,
                category = it.categoryVName,
                brandList = it.brandList.map { brand ->
                    brand.toModel()
                }
            )
        } ?: kotlin.run {
            //null 이 넘어올 때 (오류)
            StoryDetailState.Failure
        }
    }
}