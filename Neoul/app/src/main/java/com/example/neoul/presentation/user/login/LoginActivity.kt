package com.example.neoul.presentation.user.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.neoul.databinding.ActivityLoginBinding
import com.example.neoul.presentation.main.MainActivity
import com.example.neoul.presentation.user.signup.SignUpCheckActivity
import com.example.neoul.util.removeJwt
import com.example.neoul.util.removePhone
import com.example.neoul.util.removeRefresh
import com.example.neoul.util.removeSignName
import com.example.neoul.util.removeUserBirth
import com.example.neoul.util.removeUsername

class LoginActivity :AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        removeall()

        with(binding){

            btnKakaoLogin.setOnClickListener {
                //val intent = Intent(this,)
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
                val intent = Intent(this@LoginActivity,MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }

    private fun removeall(){
        removeJwt()
        removeRefresh()
        removeSignName()
        removeUsername()
        removeUserBirth()
        removePhone()
    }






}