package com.example.neoul.presentation.main.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neoul.data.model.Event
import com.example.neoul.data.model.GoodsItem
import com.example.neoul.databinding.FragmentEventBinding
import com.example.neoul.presentation.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel


class EventFragment : BaseFragment<EventViewModel, FragmentEventBinding>() {
    override val viewModel by viewModel<EventViewModel>()

    override fun getViewBinding() = FragmentEventBinding.inflate(layoutInflater)

    override fun observeDate() {
        TODO("Not yet implemented")
    }

    companion object {
        fun newInstance() = EventFragment()

        const val TAG = "EventFragment"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventAdapter(getDummyItemList())
    }

    private fun eventAdapter(itemList: ArrayList<Event>) {
        val dataRVAdapter = EventRVAdapter(itemList)

        getViewBinding().recyclerEvent.adapter = dataRVAdapter
        getViewBinding().recyclerEvent.layoutManager = LinearLayoutManager(context)
        dataRVAdapter.notifyDataSetChanged()
        getViewBinding().recyclerEvent.setHasFixedSize(true)
    }

    private fun getDummyItemList(): ArrayList<Event> {
        val dummyList = ArrayList<Event>().apply {

            add(Event("너울과 함께 해요!","너울에서 세상을 바꿔 보아요" , ""))
            add(Event("신규 회원가입 이벤트","회원가입하고 경품 가져가세요! 첫 구매 시 당첨 확률 UP!" , ""))

        }
        return dummyList
    }
}