package com.example.neoul.presentation.main.my.setting

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.neoul.databinding.ActivityMypageSettingChangeinfoBinding

class MyPageSettingChangeInfoActivity:AppCompatActivity() {
    private lateinit var binding:ActivityMypageSettingChangeinfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageSettingChangeinfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){

            btnBack.setOnClickListener {
                val intent = Intent(this@MyPageSettingChangeInfoActivity,MyPageSettingActivity::class.java)
                startActivity(intent)
                finish()
            }



        }//with(binding)

    }
}