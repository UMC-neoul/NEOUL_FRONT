package com.example.neoul.presentation.main.story

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.neoul.data.model.Story
import com.example.neoul.data.network.Url
import com.example.neoul.data.repository.story.StoryRepository
import com.example.neoul.presentation.BaseViewModel
import kotlinx.coroutines.launch

class StoryViewModel(
    private val storyRepository: StoryRepository
) : BaseViewModel() {

    val storyLiveData = MutableLiveData<List<Story>>()
    override fun fetchData() = viewModelScope.launch {
        //데스트용 더미데이터
//        val  storyList = listOf(
//            Story("독립운동가 후손,\n 현실이 이게 맞나요?","종합만족도는 소비자 만족도의 3개 부문인 서비스 품질 만족도, 상품 특성 만족도, 호감도의 중요도(가중치)를 반영한 평균값으로, 조사대상 5개 여행사의 동남아 패키지여행 서비스의 종합만족도는 평균 3.64점이었다.\n" +
//                    "사업자별로는 하나투어 3.71점, 노랑풍선 3.63점, 모두투어 3.62점, 온라인투어·인터파크투어 3.61점으로 하나투어를 제외한 나머지 4개 사업자들 간 점수 차이는 0.01~0.02점으로 매우 작은 것으로 나타났다.\n" +
//                    "서비스 품질 만족도는 평균 3.67점이었고, 사업자별로는 하나투어 3.76점, 모두투어 3.67점, 온라인투어 3.66점, 노랑풍선 3.65점, 인터파크투어 3.63점 순이었다.\n" +
//                    "상품 특성 만족도는 여행일정·숙소·이동수단, 선택관광·쇼핑·식사, 가격 및 부가혜택, 사이트·앱 이용 편리성 등 4개 요인으로 평가했는데, 여행일정·숙소·이동수단에 대한 만족도가 평균 3.77점으로 종합만족도보다 높은 반면, 선택관광·쇼핑·식사에 대한 만족도는 평균 3.38점으로 낮았다.\n" +
//                    "호감도는 평균 3.57점이었으며, 사업자별로는 하나투어 3.62점, 노랑풍선 3.58점, 모두투어 3.56점, 인터파크투어 3.54점, 온라인투어 3.53점 순이었다.","By 미롱","2023. 7. 8","aa"),
//            Story("독립운동가 후손, 현실이 이게 맞나요","bbbb","bbbb","bbb","aa"),
//            Story("독립운동가 후손, 현실이 이게 맞나요","cccc","bbbb","bbb","aa"),
//            Story("독립운동가 후손, 현실이 이게 맞나요","bbbb","bbbb","bbb","aa"),
//            Story("독립운동가 후손, 현실이 이게 맞나요","bbbb","bbbb","bbb","aa")
//        )

        val story = storyRepository.getStoryList(Url.AUTH_KEY)?.map {
            it.toModel()
        } ?: listOf()
        storyLiveData.value = story
    }
}