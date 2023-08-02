package com.example.neoul.presentation.main.story.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.neoul.data.model.BrandItem
import com.example.neoul.data.model.GoodsItem
import com.example.neoul.data.model.Story
import com.example.neoul.presentation.BaseViewModel
import kotlinx.coroutines.launch

class StoryDetailViewModel(
    private val story: Story
): BaseViewModel() {

    val storyDetailLiveData= MutableLiveData<StoryDetailState>(StoryDetailState.Uninitialized)

    override fun fetchData() =viewModelScope.launch {
        storyDetailLiveData.value = StoryDetailState.Loading
        val productList = listOf(
            GoodsItem("[핸드메이드] 푸른마음 귀걸이", 5, 16400, "몰라"),
            GoodsItem("[핸드메이드] 푸른마음 귀걸이", 5, 16400, "몰라"),
            GoodsItem("[핸드메이드] 푸른마음 귀걸이", 5, 16400, "몰라"),
            GoodsItem("[핸드메이드] 푸른마음 귀걸이", 5, 16400, "몰라"),
            GoodsItem("[핸드메이드] 푸른마음 귀걸이", 5, 16400, "몰라"),
            GoodsItem("[핸드메이드] 푸른마음 귀걸이", 5, 16400, "몰라"),
        )

        val brandList = listOf(
            BrandItem("나이키","스포츠","aa","2023.03.01",productList)
        )
        storyDetailLiveData.value = StoryDetailState.Success(
            story,
            brandList
        )
    }
}