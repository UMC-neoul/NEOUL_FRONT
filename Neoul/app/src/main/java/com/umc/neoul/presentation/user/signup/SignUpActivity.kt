package com.umc.neoul.presentation.user.signup

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.umc.neoul.data.repository.signup.SignupRepository
import com.umc.neoul.databinding.ActivitySignupBinding
import com.umc.neoul.presentation.user.signup.SignupDataFile.SignupData
import com.umc.neoul.presentation.user.signup.SignupDataFile.SignupUser
import com.umc.neoul.util.getPhone
import com.umc.neoul.util.getSignName
import com.umc.neoul.util.getUserBirth
import com.umc.neoul.util.getUsername
import kotlinx.coroutines.launch
import org.jetbrains.annotations.NotNull
import org.koin.android.ext.android.bind
import org.koin.android.ext.android.inject
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    var email_check = false
    var password_check = false
    var password_double_check = false

    private val signUpApi by inject<SignupRepository>()

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
                        email_check = emailcheck()
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
                signup()
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


    val emailValidation =  "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    private fun emailcheck() : Boolean{
        var email = binding.editEmail.text.toString().trim()
        val p = Pattern.matches(emailValidation, email)
        return p
    }

    private fun signup(){
        lifecycleScope.launch {
            val signUpData = signUpApi.signup(getSignUpData())
            if(signUpData?.code == 200){
                startSuccessActivity()
            }else{
                Toast.makeText(this@SignUpActivity,signUpData?.message.toString(), Toast.LENGTH_SHORT)
                    .show()
            }


        }
    }

    private fun getSignUpData() : SignupUser{
        val password = binding.editPassword.text.toString()
        val email = binding.editEmail.text.toString()

        return SignupUser(birth = getUserBirth()!!, imageUrl = "", name = getSignName()!!, password = password, username = email, phone = getPhone()!! )
    }

    private fun startSuccessActivity(){
        val intent = Intent(this@SignUpActivity,SignUpFinishActivity::class.java)
        startActivity(intent)
    }


}