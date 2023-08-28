package com.umc.neoul.data.response.story.detail

data class Data(
    val brandList: List<Brand>,
    val categoryVName: String,
    val content: String,
    val createdAt: String,
    val preImg: String,
    val storyId: Int,
    val title: String
)