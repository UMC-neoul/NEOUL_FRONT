package com.example.neoul.presentation.user.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.neoul.databinding.ActivityLoginEmailBinding

class LoginEmailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginEmailBinding

    //아이디, 비밀번호 입력시 판단 기준
    var et_email = false
    var et_password = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //바인딩 귀찮음
        with(binding){

            //이메일 길이 확인
            editEmail.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    if(editEmail.length()>3){
                        et_email = true
                        logincheck()
                    }
                    else{
                        et_email = false
                        logincheck()
                    }
                }

            })

            //비밀번호 길이 확인
            editPassword.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    if(editPassword.length()>5){
                        et_password = true
                        logincheck()
                    }
                    else{
                        et_password = false
                        logincheck()
                    }
                }
            })

            btnBack.setOnClickListener {
                val intent = Intent(this@LoginEmailActivity,LoginActivity::class.java)
                startActivity(intent)
            }



        }//with(binding)

    }//onCreate

    //전역함수를 통해 로그인 가능 여부 확인하는 함수
    private fun logincheck(){
        if(et_email){
            binding.btnLogin.isEnabled = et_password
        }
        else{
            binding.btnLogin.isEnabled = false
        }
    }

}



/*To Do
* 1. api연결
* 2. 오류 발생시 텍스트 변환
* 3. 색 변환
* */









