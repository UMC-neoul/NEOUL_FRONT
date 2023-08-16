package com.example.neoul.presentation.main.header

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.neoul.databinding.ActivitySearchBinding

class SearchActivity: AppCompatActivity() {
    private lateinit var viewBinding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySearchBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        viewBinding.imgBack.setOnClickListener {
            finish()
        }

    }
}