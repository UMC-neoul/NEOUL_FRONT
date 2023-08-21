package com.example.neoul.presentation.main.my

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.neoul.data.repository.mypage.MyPageRepository
import com.example.neoul.presentation.BaseViewModel
import com.example.neoul.presentation.main.my.data.MyPageItem
import com.example.neoul.presentation.main.my.data.MyPageProduct
import com.example.neoul.util.getJwt
import kotlinx.coroutines.launch

class MyPageViewModel(
    private val myPageRepository: MyPageRepository
) : BaseViewModel() {

    val mypageLiveData = MutableLiveData<List<MyPageProduct>>()

    override fun fetchData() = viewModelScope.launch {

        if(getJwt() != null){
            val mypageList = myPageRepository.mypageproduct(getJwt()!!)?.data?.map{
                it.toModel()
            }
            mypageLiveData.value = mypageList
        }

    }





}

//뷰모델로 api전부 받고 liveData를 받아서 activity구현