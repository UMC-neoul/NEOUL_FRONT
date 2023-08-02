package com.example.neoul.presentation.user.signup

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.neoul.databinding.ActivitySignupBinding
import org.jetbrains.annotations.NotNull
import org.koin.android.ext.android.bind

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    var email_check = false
    var password_check = false
    var password_double_check = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)




        with(binding){


            editEmail.addTextChangedListener(object :TextWatcher{
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
                    if(editEmail.length()>5){
                        email_check = true
                        signup_check()
                    }else{
                        email_check = false
                        signup_check()
                    }
                }

            })

            editPassword.addTextChangedListener(object :TextWatcher{
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
                        password_check = true
                        signup_check()
                    }else{
                        password_check = false
                        signup_check()
                    }
                }
            })

            editPasscheck.addTextChangedListener(object :TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    txtPasscheck.text = "비밀번호 확인"
                    txtPasscheck.setTextColor(Color.parseColor("#5D5E61"))
                }

                override fun afterTextChanged(s: Editable?) {
                    if(editPasscheck.text.toString() == editPassword.text.toString()){
                        password_double_check = true
                        signup_check()
                    }else{
                        password_double_check = false
                        txtPasscheck.text = "비밀번호가 일치하지 않습니다."
                        txtPasscheck.setTextColor(Color.parseColor("#BA1A1A"))
                        signup_check()
                    }

                }

            })

            btnNext.setOnClickListener {
                val intent = Intent(this@SignUpActivity,SignUpFinishActivity::class.java)
                startActivity(intent)
            }

            btnBack.setOnClickListener {
                val intent = Intent(this@SignUpActivity,CheckPrivacySignUpActivity::class.java)
                startActivity(intent)
            }

        }//with(binding)

    }//onCreate

    private fun signup_check(){
        if(email_check){
            if(password_check){
                binding.btnNext.isEnabled = password_double_check
                binding.btnNext.setTextColor(Color.parseColor("#ffffff"))
            }else{
                binding.btnNext.isEnabled = false
                binding.btnNext.setTextColor(Color.parseColor("#909094"))
            }
        }
        else{
            binding.btnNext.isEnabled = false
            binding.btnNext.setTextColor(Color.parseColor("#909094"))
        }
    }





}