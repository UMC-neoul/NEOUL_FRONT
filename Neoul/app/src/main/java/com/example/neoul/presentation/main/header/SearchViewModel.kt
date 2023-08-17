package com.example.neoul.presentation.main.header

import androidx.lifecycle.viewModelScope
import com.example.neoul.presentation.BaseViewModel
import kotlinx.coroutines.launch

class SearchViewModel : BaseViewModel() {

    override fun fetchData() = viewModelScope.launch {

    }
}