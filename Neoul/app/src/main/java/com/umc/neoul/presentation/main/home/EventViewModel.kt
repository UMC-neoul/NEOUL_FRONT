package com.umc.neoul.presentation.main.home

import androidx.lifecycle.viewModelScope
import com.umc.neoul.presentation.BaseViewModel
import kotlinx.coroutines.launch

class EventViewModel:BaseViewModel() {
    override fun fetchData() = viewModelScope.launch {

    }
}