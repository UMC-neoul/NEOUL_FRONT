package com.example.neoul.presentation.user.login

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.neoul.databinding.ActivityLoginEmailBinding
import com.example.neoul.presentation.user.signup.PreferenceActivity
import org.koin.android.ext.android.bind

class LoginEmailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginEmailBinding

    //아이디, 비밀번호 입력시 판단 기준
    var et_email = false
    var et_password = false

    var id_ = ""
    var password_ = ""
    
    var id_check = false
    var password_check = false

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

            btnLogin.setOnClickListener {
                login()
                if(id_ == "test@naver.com"){
                    finalcheck()
                    id_check = true
                    txtEmail.text = "이메일"
                    txtEmail.setTextColor(Color.parseColor("#5D5E61"))
                }
                else{
                    txtEmail.text = "이메일 주소를 입력해 주세요."
                    txtEmail.setTextColor(Color.parseColor("#BA1A1A"))
                    id_check = false
                }
                
                if(password_ == "123456"){
                    finalcheck()
                    password_check = true
                    txtPassword.text = "비밀번호"
                    txtPassword.setTextColor(Color.parseColor("#5D5E61"))
                }
                else{
                    txtPassword.text = "6자 이상의 비밀번호를 입력해세요."
                    password_check = false
                    txtPassword.setTextColor(Color.parseColor("#BA1A1A"))
                }
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

    private fun login(){
        id_ = binding.editEmail.text.toString()
        password_ = binding.editPassword.text.toString()
    }
    
    private fun finalcheck(){
        if(id_check){
            if(password_check){
                val intent = Intent(this,PreferenceActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

}



/*To Do
* 1. api연결
* 2. 오류 발생시 텍스트 변환
* 3. 색 변환
* */









