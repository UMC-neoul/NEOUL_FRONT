package com.umc.neoul.presentation.user.signup

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.umc.neoul.databinding.ActivityPreferenceBinding
import com.umc.neoul.presentation.main.MainActivity

class PreferenceActivity:AppCompatActivity() {
    private lateinit var binding:ActivityPreferenceBinding

    var checksum = 0
    var check = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //부끄럽지만 똑똑한 방법이 떠오르지 않을땐 하드코딩
        with(binding){
            cardViewImg1.setOnClickListener {
                if(check){
                    if(img1Check.isChecked){
                        checksum--
                        img1Check.isChecked = false
                        check()
                    }else{
                        img1Check.isChecked = true
                        checksum++
                        check()
                    }
                }else{
                    if(img1Check.isChecked){
                        checksum--
                        img1Check.isChecked = false
                        check()
                    }
                }

            }

            cardViewImg2.setOnClickListener {
                if(check){
                    if(img2Check.isChecked){
                        checksum--
                        img2Check.isChecked = false
                        check()
                    }else{
                        img2Check.isChecked = true
                        checksum++
                        check()
                    }
                }else{
                    if(img2Check.isChecked){
                        checksum--
                        img2Check.isChecked = false
                        check()
                    }
                }

            }

            cardViewImg3.setOnClickListener {
                if(check){
                    if(img3Check.isChecked){
                        checksum--
                        img3Check.isChecked = false
                        check()
                    }else{
                        img3Check.isChecked = true
                        checksum++
                        check()
                    }
                }else{
                    if(img3Check.isChecked){
                        checksum--
                        img3Check.isChecked = false
                        check()
                    }
                }
            }

            cardViewImg4.setOnClickListener {
                if(check){
                    if(img4Check.isChecked){
                        checksum--
                        img4Check.isChecked = false
                        check()
                    }else{
                        img4Check.isChecked = true
                        checksum++
                        check()
                    }
                }else{
                    if(img4Check.isChecked){
                        checksum--
                        img4Check.isChecked = false
                        check()
                    }
                }
            }

            cardViewImg5.setOnClickListener {
                if(check){
                    if(img5Check.isChecked){
                        checksum--
                        img5Check.isChecked = false
                        check()
                    }else{
                        img5Check.isChecked = true
                        checksum++
                        check()
                    }
                }else{
                    if(img5Check.isChecked){
                        checksum--
                        img5Check.isChecked = false
                        check()
                    }
                }
            }

            cardViewImg6.setOnClickListener {
                if(check){
                    if(img6Check.isChecked){
                        checksum--
                        img6Check.isChecked = false
                        check()
                    }else{
                        img6Check.isChecked = true
                        checksum++
                        check()
                    }
                }else{
                    if(img6Check.isChecked){
                        checksum--
                        img6Check.isChecked = false
                        check()
                    }
                }
            }

            cardViewImg7.setOnClickListener {
                if(check){
                    if(img7Check.isChecked){
                        checksum--
                        img7Check.isChecked = false
                        check()
                    }else{
                        img7Check.isChecked = true
                        checksum++
                        check()
                    }
                }else{
                    if(img7Check.isChecked){
                        checksum--
                        img7Check.isChecked = false
                        check()
                    }
                }
            }

            cardViewImg8.setOnClickListener {
                if(check){
                    if(img8Check.isChecked){
                        checksum--
                        img8Check.isChecked = false
                        check()
                    }else{
                        img8Check.isChecked = true
                        checksum++
                        check()
                    }
                }else{
                    if(img8Check.isChecked){
                        checksum--
                        img8Check.isChecked = false
                        check()
                    }
                }
            }

            cardViewImg9.setOnClickListener {
                if(check){
                    if(img9Check.isChecked){
                        checksum--
                        img9Check.isChecked = false
                        check()
                    }else{
                        img9Check.isChecked = true
                        checksum++
                        check()
                    }
                }else{
                    if(img9Check.isChecked){
                        checksum--
                        img9Check.isChecked = false
                        check()
                    }
                }
            }

            cardViewImg10.setOnClickListener {
                if(check){
                    if(img10Check.isChecked){
                        checksum--
                        img10Check.isChecked = false
                        check()
                    }else{
                        img10Check.isChecked = true
                        checksum++
                        check()
                    }
                }else{
                    if(img10Check.isChecked){
                        checksum--
                        img10Check.isChecked = false
                        check()
                    }
                }
            }

            cardViewImg11.setOnClickListener {
                if(check){
                    if(img11Check.isChecked){
                        checksum--
                        img11Check.isChecked = false
                        check()
                    }else{
                        img11Check.isChecked = true
                        checksum++
                        check()
                    }
                }else{
                    if(img11Check.isChecked){
                        checksum--
                        img11Check.isChecked = false
                        check()
                    }
                }
            }

            cardViewImg12.setOnClickListener {
                if(check){
                    if(img12Check.isChecked){
                        checksum--
                        img12Check.isChecked = false
                        check()
                    }else{
                        img12Check.isChecked = true
                        checksum++
                        check()
                    }
                }else{
                    if(img12Check.isChecked){
                        checksum--
                        img12Check.isChecked = false
                        check()
                    }
                }
            }

            cardViewImg13.setOnClickListener {
                if(check){
                    if(img13Check.isChecked){
                        checksum--
                        img13Check.isChecked = false
                        check()
                    }else{
                        img13Check.isChecked = true
                        checksum++
                        check()
                    }
                }else{
                    if(img13Check.isChecked){
                        checksum--
                        img13Check.isChecked = false
                        check()
                    }
                }
            }

            cardViewImg14.setOnClickListener {
                if(check){
                    if(img14Check.isChecked){
                        checksum--
                        img14Check.isChecked = false
                        check()
                    }else{
                        img14Check.isChecked = true
                        checksum++
                        check()
                    }
                }else{
                    if(img14Check.isChecked){
                        checksum--
                        img14Check.isChecked = false
                        check()
                    }
                }
            }

            cardViewImg15.setOnClickListener {
                if(check){
                    if(img15Check.isChecked){
                        checksum--
                        img15Check.isChecked = false
                        check()
                    }else{
                        img15Check.isChecked = true
                        checksum++
                        check()
                    }
                }else{
                    if(img15Check.isChecked){
                        checksum--
                        img15Check.isChecked = false
                        check()
                    }
                }
            }

            btnFinish.setOnClickListener {
                val intent = Intent(this@PreferenceActivity,MainActivity::class.java)
                startActivity(intent)
            }


        }//with(binding)

    }

    private fun check(){
        if(checksum>2){
            check = false
            binding.btnFinish.text = "($checksum/3) 선택 완료"
            binding.btnFinish.isEnabled = true
            binding.btnFinish.setTextColor(Color.parseColor("#ffffff"))

        }else{
            check = true
            binding.btnFinish.text = "($checksum/3) 선택 완료"
            binding.btnFinish.isEnabled = false
            binding.btnFinish.setTextColor(Color.parseColor("#909094"))
        }
    }
}