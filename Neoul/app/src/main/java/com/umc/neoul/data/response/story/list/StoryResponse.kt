package com.umc.neoul.data.response.story.list

data class StoryResponse(
    val code: Int,
    val data: List<Data>,
    val message: String
)