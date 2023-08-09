package com.example.neoul.presentation.user.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.neoul.data.repository.test.TestRepository
import com.example.neoul.data.response.test.login.request.LoginRequest
import com.example.neoul.data.response.test.signup.request.SignupRequest
import com.example.neoul.databinding.ActivityLoginBinding
import com.example.neoul.presentation.main.MainActivity
import com.example.neoul.presentation.user.signup.SignUpCheckActivity
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class LoginActivity :AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding


//test
    private val testApi by inject<TestRepository> ()
    private val requestLogin = LoginRequest(
        username = "test@naver.com",
        password = "123456"
    )
    private val requestSignup = SignupRequest(
        birth = "20000309",
        imageUrl = "",
        name = "hgh",
        password = "123456",
        phone = "01011111111",
        username = "test@naver.com"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        with(binding){

            btnKakaoLogin.setOnClickListener {
                //val intent = Intent(this,)
                lifecycleScope.launch {
                    testApi.signup(requestSignup)
                }
            }
            btnEmailLogin.setOnClickListener {
                val intent = Intent(this@LoginActivity,LoginEmailActivity::class.java)
                startActivity(intent)

            }

            btnEmailSignup.setOnClickListener {
                val intent = Intent(this@LoginActivity,SignUpCheckActivity::class.java)
                startActivity(intent)
            }

            btnNoidLogin.setOnClickListener {
                lifecycleScope.launch {
                    testApi.login(requestLogin)
                }
                val intent = Intent(this@LoginActivity,MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }

    //loginApi test
}