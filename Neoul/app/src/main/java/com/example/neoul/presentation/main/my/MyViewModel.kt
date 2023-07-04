package com.example.neoul.presentation.main.my

import androidx.lifecycle.viewModelScope
import com.example.neoul.presentation.BaseViewModel
import kotlinx.coroutines.launch

class MyViewModel : BaseViewModel() {

    override fun fetchData() = viewModelScope.launch {

    }
}