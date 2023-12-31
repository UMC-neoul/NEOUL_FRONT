package com.umc.neoul.presentation.main.story.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.umc.neoul.data.model.Story
import com.umc.neoul.data.network.Url
import com.umc.neoul.data.repository.story.StoryRepository
import com.umc.neoul.presentation.BaseViewModel
import com.umc.neoul.presentation.main.brand.detail.BrandDetailState
import com.umc.neoul.util.getJwt
import kotlinx.coroutines.launch

class StoryDetailViewModel(
    private val story: Story,
    private val storyRepository: StoryRepository
) : BaseViewModel() {

    private var jwt = ""

    val storyDetailLiveData = MutableLiveData<StoryDetailState>(StoryDetailState.Uninitialized)

    override fun fetchData() = viewModelScope.launch {

        //accessToken 가져오기 (비회원일경우 로그인 페이지로)
        if (getJwt().isNullOrEmpty()){
            storyDetailLiveData.value = StoryDetailState.NotAuth
            return@launch
        }else{
            jwt = getJwt().toString()
        }

        //STORY DETAIL GET
        storyRepository.getStoryDetail(jwt, story.id)?.let {
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