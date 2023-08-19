package com.example.neoul.presentation.main.my.setting

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.neoul.data.repository.mypage.MyPageRepository
import com.example.neoul.databinding.ActivityMypageSettingChangeinfoBinding
import com.example.neoul.presentation.main.my.Info.UserInfoData
import com.example.neoul.presentation.user.login.LoginActivity
import com.example.neoul.util.getJwt
import com.example.neoul.util.getPhone
import com.example.neoul.util.getSignName
import com.example.neoul.util.getUserBirth
import com.example.neoul.util.getUsername
import kotlinx.coroutines.launch
import org.koin.android.ext.android.bind
import org.koin.android.ext.android.inject

class MyPageSettingChangeInfoActivity:AppCompatActivity() {
    private lateinit var binding:ActivityMypageSettingChangeinfoBinding

    private val myPageInfoChangeApi by inject<MyPageRepository>()

    var checkname = false
    var checkbirth = false
    var checkphone = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageSettingChangeinfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){

            checkinfo()


            btnBack.setOnClickListener {
                val intent = Intent(this@MyPageSettingChangeInfoActivity,MyPageSettingActivity::class.java)
                startActivity(intent)
                finish()
            }

            editName.addTextChangedListener(object : TextWatcher{
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
                    if(editName.length() > 1){
                        checkname = true
                        checkok()
                    }else{
                        checkname = false
                    }

                }


            })

            editPhonenum.addTextChangedListener(object : TextWatcher{
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
                        checkphone = true
                        checkok()
                    }else{
                        checkphone = false
                    }
                }

            })

            editBirth.addTextChangedListener(object: TextWatcher{
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
                    if(editBirth.length() == 8){
                        checkbirth = true
                        checkok()
                    }else{
                        checkbirth = false
                    }
                }

            })





            btnNext.setOnClickListener {
                changeInfo()
            }

        }//with(binding)

    }

    private fun checkok(){
        if(checkname){
            if(checkbirth){
                binding.btnNext.isEnabled = checkphone
            }else{
                binding.btnNext.isEnabled = false
            }
        }else{
            binding.btnNext.isEnabled = false
        }
    }
    private fun checkinfo(){

        binding.editName.setText(getUsername())
        binding.editBirth.setText(getUserBirth())
        binding.editPhonenum.setText(getPhone())




    }

    private fun changeInfo(){

        lifecycleScope.launch {
            val infodata = myPageInfoChangeApi.mypageinfochange(getJwt()!!,getinfo())
            if(infodata!!.code == 200){
                Toast.makeText(this@MyPageSettingChangeInfoActivity,infodata?.message.toString(), Toast.LENGTH_SHORT)
                    .show()
                startSuccessActivity()
            }else{
                Toast.makeText(this@MyPageSettingChangeInfoActivity,infodata?.message.toString(), Toast.LENGTH_SHORT)
                    .show()
            }

        }


    }

    private fun getinfo() : UserInfoData{
        val birth = binding.editBirth.text.toString()
        val name = binding.editName.text.toString()
        val phone = binding.editPhonenum.text.toString()


        return UserInfoData(birth = birth, name = name, phone = phone)
    }

    private fun startSuccessActivity(){
        val intent = Intent(this@MyPageSettingChangeInfoActivity,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }







}