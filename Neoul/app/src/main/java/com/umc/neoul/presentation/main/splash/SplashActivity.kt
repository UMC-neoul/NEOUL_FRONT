package com.umc.neoul.presentation.main.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.umc.neoul.data.repository.login.LoginRepository
import com.umc.neoul.databinding.ActivitySplashBinding
import com.umc.neoul.presentation.main.MainActivity
import com.umc.neoul.presentation.user.login.LoginActivity
import com.umc.neoul.util.getJwt
import org.koin.android.ext.android.inject

class SplashActivity:AppCompatActivity() {
    private lateinit var binding:ActivitySplashBinding

    private val loginApi by inject<LoginRepository>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)



        Handler(Looper.getMainLooper()).postDelayed({
            if(getJwt().isNullOrBlank()){
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }else {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        },1500)

    }







}