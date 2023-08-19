package com.example.neoul.presentation.main.my.setting

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.neoul.databinding.ActivityMypageSettingBinding
import com.example.neoul.presentation.main.MainActivity
import com.example.neoul.presentation.user.login.LoginActivity

class MyPageSettingActivity:AppCompatActivity() {
    private lateinit var binding:ActivityMypageSettingBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){

            btnBack.setOnClickListener {
                //val intent = Intent(this@MyPageSettingActivity,MainActivity::class.java)
                //startActivity(intent)
                //finish()
                onBackPressed()//onbackpressdispatcher? 로 교채 예정(알아보는중)
            }

            btnLogout.setOnClickListener {
                val intent = Intent(this@MyPageSettingActivity,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }


            btnPrivacyChange.setOnClickListener {
                val intent = Intent(this@MyPageSettingActivity,MyPageSettingChangeInfoActivity::class.java)
                startActivity(intent)
            }

        }//with(binding)

    }


    //뒤로가기
    override fun onBackPressed(){
        super.onBackPressed()
    }


}