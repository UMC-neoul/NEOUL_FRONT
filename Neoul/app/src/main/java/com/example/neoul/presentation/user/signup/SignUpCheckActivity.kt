package com.example.neoul.presentation.user.signup

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.neoul.databinding.ActivitySignupCheckBinding
import org.koin.android.ext.android.bind

class SignUpCheckActivity:AppCompatActivity() {
    private lateinit var binding:ActivitySignupCheckBinding

    var all_check = false
    var first_check = false
    var second_check = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){


            checkFirst.setOnClickListener {
                first_check = !first_check
                checknext()

            }
            checkSecond.setOnClickListener {
                second_check = !second_check
                checknext()

            }
            checkAll.setOnClickListener {
                all_check = !all_check
                if(all_check){
                    checkFirst.isChecked = true
                    first_check = true
                    checkSecond.isChecked = true
                    second_check = true
                    checknext()
                }
                else{
                    checkFirst.isChecked = false
                    first_check = false
                    checkSecond.isChecked = false
                    second_check = false
                    checknext()
                }
            }



            btnNext.setOnClickListener {
                var intent = Intent(this@SignUpCheckActivity, CheckPrivacySignUpActivity::class.java)
                startActivity(intent)
            }
        }//with(binding)

    }//onCreate

    private fun checknext(){
        if(first_check){
            if(second_check){
                all_check = true
                binding.checkAll.isChecked = true
            }
            else{
                all_check = false
                binding.checkAll.isChecked = false
            }
        }
        else{
            all_check = false
            binding.checkAll.isChecked = false
        }

        if(all_check){
            binding.btnNext.isEnabled = true
            binding.btnNext.setTextColor(Color.parseColor("#FFFFFF"))
        }
        else{
            binding.btnNext.isEnabled = false
            binding.btnNext.setTextColor(Color.parseColor("#909094"))
        }
    }

}