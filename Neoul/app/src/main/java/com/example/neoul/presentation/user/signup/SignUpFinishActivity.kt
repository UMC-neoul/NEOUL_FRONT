package com.example.neoul.presentation.user.signup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.neoul.databinding.ActivitySignupFinishBinding
import com.example.neoul.presentation.user.login.LoginActivity
import com.example.neoul.presentation.user.login.LoginEmailActivity

class SignUpFinishActivity:AppCompatActivity() {
    private lateinit var binding:ActivitySignupFinishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupFinishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            btnToLogin.setOnClickListener {
                val intent = Intent(this@SignUpFinishActivity,LoginEmailActivity::class.java)
                startActivity(intent)
                finish()
            }

            btnToHome.setOnClickListener {
                val intent = Intent(this@SignUpFinishActivity,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }//with(binding)
    }//onCreate
}


/*이부분 질문
* 홈으로와 로그인 이동 위치 모르겠음.
* 화면의 이동 순서를 간단하게 화살표로 만들어 주었으면
*
* */