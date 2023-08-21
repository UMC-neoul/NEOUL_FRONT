package com.example.neoul.presentation.main.header

sealed class SearchState{
    object SearchBefore: SearchState()

    data class SearchAfter(
        val size : Int
    ): SearchState()

    object Failure : SearchState()

    object NoAuth : SearchState()
}