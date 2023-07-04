package com.example.neoul.presentation.main.category

import androidx.lifecycle.viewModelScope
import com.example.neoul.presentation.BaseViewModel
import kotlinx.coroutines.launch

class CategoryViewModel : BaseViewModel() {

    override fun fetchData() = viewModelScope.launch {

    }
}