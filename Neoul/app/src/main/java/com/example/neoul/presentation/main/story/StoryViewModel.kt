package com.example.neoul.presentation.main.story

import androidx.lifecycle.viewModelScope
import com.example.neoul.presentation.BaseViewModel
import kotlinx.coroutines.launch

class StoryViewModel : BaseViewModel() {

    override fun fetchData() = viewModelScope.launch {

    }
}