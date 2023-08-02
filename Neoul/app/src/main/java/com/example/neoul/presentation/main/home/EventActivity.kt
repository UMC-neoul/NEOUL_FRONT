package com.example.neoul.presentation.main.home

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.neoul.data.model.Event
import com.example.neoul.databinding.ActivityEventBinding

class EventActivity:AppCompatActivity() {
    private lateinit var viewBinding: ActivityEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityEventBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)
        eventAdapter(getDummyItemList())
    }



    private fun eventAdapter(itemList: ArrayList<Event>) {
        val dataRVAdapter = EventRVAdapter(itemList)

        viewBinding.recyclerEvent.adapter = dataRVAdapter
        viewBinding.recyclerEvent.layoutManager = LinearLayoutManager(this)
        dataRVAdapter.notifyDataSetChanged()
        viewBinding.recyclerEvent.setHasFixedSize(true)
    }

    private fun getDummyItemList(): ArrayList<Event> {
        val dummyList = ArrayList<Event>().apply {

            add(Event("너울과 함께 해요!","너울에서 세상을 바꿔 보아요" , ""))
            add(Event("신규 회원가입 이벤트","회원가입하고 경품 가져가세요! 첫 구매 시 당첨 확률 UP!" , ""))

        }
        return dummyList
    }
}