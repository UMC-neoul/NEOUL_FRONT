package com.umc.neoul.presentation.user.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.umc.neoul.databinding.ActivityLoginBinding
import com.umc.neoul.presentation.main.MainActivity
import com.umc.neoul.presentation.user.signup.SignUpCheckActivity
import com.umc.neoul.util.getkakaoLogin
import com.umc.neoul.util.removeJwt
import com.umc.neoul.util.removePhone
import com.umc.neoul.util.removeRefresh
import com.umc.neoul.util.removeSignName
import com.umc.neoul.util.removeUserBirth
import com.umc.neoul.util.removeUsername
import com.umc.neoul.util.removekakaoLogin
import com.umc.neoul.util.savekakaoLogin
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient

class LoginActivity :AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        removeall()




        with(binding){

            btnKakaoLogin.setOnClickListener {

                val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                    Log.d("Tester", "onCreate: 일단 확인")
                    if (error != null) {
                        Log.d("Tester", "카카오계정으로 로그인 실패", error)
                    } else if (token != null) {
                        Log.d("Tester", "카카오계정으로 로그인 성공 ${token.accessToken}")
                        val intent = Intent(this@LoginActivity,MainActivity::class.java)
                        savekakaoLogin(true)
                        startActivity(intent)

                        finish()
                    }
                }
                Log.d("Tester", "onCreate: ")

                // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
                if (UserApiClient.instance.isKakaoTalkLoginAvailable(this@LoginActivity)) {

                    UserApiClient.instance.loginWithKakaoTalk(this@LoginActivity) { token, error ->
                        if (error != null) {
                            Log.d("Tester", "카카오톡으로 로그인 실패", error)

                            // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                            // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                            if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                                return@loginWithKakaoTalk
                            }

                            // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                            UserApiClient.instance.loginWithKakaoAccount(this@LoginActivity, callback = callback)
                        } else if (token != null) {
                            Log.d("Tester", "카카오톡으로 로그인 성공 ${token.accessToken}")

                            val intent = Intent(this@LoginActivity,MainActivity::class.java)
                            savekakaoLogin(true)
                            startActivity(intent)

                            finish()
                        }
                    }
                } else {
                    UserApiClient.instance.loginWithKakaoAccount(this@LoginActivity, callback = callback)
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
        removekakaoLogin()
    }






}