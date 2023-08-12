package com.example.neoul.presentation.user.login

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.neoul.data.repository.login.LoginRepository
import com.example.neoul.databinding.ActivityLoginEmailBinding
import com.example.neoul.presentation.main.MainActivity
import com.example.neoul.presentation.user.login.LoginDataFile.Data
import com.example.neoul.presentation.user.login.LoginDataFile.User
import com.example.neoul.presentation.user.signup.PreferenceActivity
import com.example.neoul.util.saveJwt
import com.example.neoul.util.saveRefresh
import com.example.neoul.util.saveUsername
import kotlinx.coroutines.launch
import org.koin.android.ext.android.bind
import org.koin.android.ext.android.inject
import kotlin.math.log

class LoginEmailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginEmailBinding

    private val loginApi by inject<LoginRepository>()


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
        lifecycleScope.launch {
            val loginData = loginApi.login(getUser())
            Log.d("Tester", "login:12312 ${loginData?.message}")
            if(loginData?.code == 200 && loginData?.data!!.firstLogin){
                startFirstLoginActivity()
            }else if(loginData?.code == 200){
                startSuccessActivity()
            }else{
                Toast.makeText(this@LoginEmailActivity,loginData?.message.toString(),Toast.LENGTH_SHORT)
                    .show()
            }
        }



    }

    private fun getUser(): User {
        var id = binding.editEmail.text.toString()
        var password = binding.editPassword.text.toString()

        return User(username = id, password = password)
    }
    
    private fun startSuccessActivity(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun startFirstLoginActivity(){
        val intent = Intent(this,PreferenceActivity::class.java)
        startActivity(intent)
        finish()
    }

}



/*To Do
* 1. api연결
* 2. 오류 발생시 텍스트 변환
* 3. 색 변환
* */









