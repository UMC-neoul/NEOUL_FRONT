package com.example.neoul.presentation.main.home

import androidx.lifecycle.viewModelScope
import com.example.neoul.presentation.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    override fun fetchData() = viewModelScope.launch {

    }
}