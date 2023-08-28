package com.umc.neoul.presentation.user.signup

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.umc.neoul.databinding.ActivitySignupPrivacyCheckBinding
import com.umc.neoul.presentation.user.login.LoginActivity
import com.umc.neoul.util.savePhone
import com.umc.neoul.util.saveSignName
import com.umc.neoul.util.saveUserBirth

class CheckPrivacySignUpActivity:AppCompatActivity() {
    private lateinit var binding:ActivitySignupPrivacyCheckBinding

    var edt_name = false
    var edt_birth = false
    var edt_phone = false
    var last_check = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupPrivacyCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){

            editName.addTextChangedListener(object :TextWatcher{
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
                    if(editName.length()>1){
                        edt_name = true
                        checkprivacy()
                    }
                    else{
                        edt_name = false
                        checkprivacy()
                    }
                }

            })

            editBirth.addTextChangedListener(object :TextWatcher{
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
                    if(editBirth.length()==8 ){
                        edt_birth = true
                        checkprivacy()
                    }else{
                        edt_birth = false
                        checkprivacy()
                    }

                }

            })

            editPhonenum.addTextChangedListener(object :TextWatcher{
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
                    if(editPhonenum.length() == 11){
                        edt_phone = true
                        checkprivacy()
                    }else{
                        edt_phone = false
                        checkprivacy()
                    }
                }

            })

            /*if(last_check){
                btnNext.isEnabled = true
            }*/

            btnBack.setOnClickListener {
                val intent = Intent(this@CheckPrivacySignUpActivity,LoginActivity::class.java)
                finish()
                startActivity(intent)
            }

            btnNext.setOnClickListener {
                val intent = Intent(this@CheckPrivacySignUpActivity,SignUpActivity::class.java)
                savePhone(editPhonenum.text.toString())
                saveUserBirth(editBirth.text.toString())
                saveSignName(editName.text.toString())
                startActivity(intent)
            }





        }//with(binding)

    }//onCreate

    private fun checkprivacy(){
        if(edt_name){
            if(edt_birth){
                if(edt_phone){
                    binding.btnNext.isEnabled = true
                    binding.btnNext.setTextColor(Color.parseColor("#ffffff"))
                }
                else{
                    binding.btnNext.isEnabled = false
                    binding.btnNext.setTextColor(Color.parseColor("#909094"))
                }
            }
            else{
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


