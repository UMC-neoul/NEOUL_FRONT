package com.umc.neoul.data.response.story.list

import com.umc.neoul.data.model.Story

data class Data(
    val categoryVName: String,
    val createdAt: String,
    val preImg: String,
    val storyId: Int,
    val title: String
){
    fun toModel()=Story(
        id = storyId,
        title = title,
        date = createdAt,
        category = categoryVName,
        image = preImg
    )
}