package com.example.neoul.presentation.main.my

import androidx.lifecycle.viewModelScope
import com.example.neoul.presentation.BaseViewModel
import kotlinx.coroutines.launch

class MyPageViewModel : BaseViewModel() {

    override fun fetchData() = viewModelScope.launch {

    }
}

//뷰모델로 api전부 받고 liveData를 받아서 activity구현