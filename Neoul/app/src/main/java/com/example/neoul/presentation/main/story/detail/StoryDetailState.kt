package com.example.neoul.presentation.main.story.detail

import com.example.neoul.data.model.BrandItem
import com.example.neoul.data.model.Story

sealed class StoryDetailState {
    object Uninitialized : StoryDetailState()

    object Loading: StoryDetailState()

    object Failure: StoryDetailState()

    data class Success(
        val story: Story,
        val content: String,
        val category: String,
        val brandList: List<BrandItem>
    ) : StoryDetailState()
}