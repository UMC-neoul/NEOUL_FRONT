package com.example.neoul.presentation.main.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.neoul.data.repository.login.LoginRepository
import com.example.neoul.databinding.ActivitySplashBinding
import com.example.neoul.presentation.main.MainActivity
import com.example.neoul.presentation.user.login.LoginActivity
import com.example.neoul.presentation.user.signup.PreferenceActivity
import com.example.neoul.util.getJwt
import kotlinx.coroutines.launch
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