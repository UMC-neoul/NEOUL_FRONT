package com.umc.neoul.presentation.main.my.setting

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.appcompat.app.AppCompatActivity
import com.umc.neoul.databinding.ActivityMypageSettingBinding
import com.umc.neoul.presentation.main.MainActivity
import com.umc.neoul.presentation.user.login.LoginActivity
import com.umc.neoul.util.getkakaoLogin
import com.umc.neoul.util.removekakaoLogin
import com.kakao.sdk.user.UserApiClient

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
                if(getkakaoLogin()!!){
                    UserApiClient.instance.unlink { error ->
                        if (error != null) {
                            Log.e("Hello", "연결 끊기 실패", error)
                        } else {
                            Log.i("Hello", "연결 끊기 성공. SDK에서 토큰 삭제 됨")

                        }
                    }

                }
                startActivity(intent)
                Toast.makeText(this@MyPageSettingActivity,"로그아웃 성공",Toast.LENGTH_SHORT).show()
                finish()
            }


            btnPrivacyChange.setOnClickListener {
                if(getkakaoLogin()!!){
                    Toast.makeText(this@MyPageSettingActivity,"카카오 계정은 수정할 수 없습니다.",Toast.LENGTH_SHORT).show()
                }else{
                    val intent = Intent(this@MyPageSettingActivity,MyPageSettingChangeInfoActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            }

        }//with(binding)

    }


    //뒤로가기
    override fun onBackPressed(){
        super.onBackPressed()
    }


}