package com.example.neoul.presentation.main.header

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.neoul.databinding.ActivitySearchBinding
import com.example.neoul.presentation.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SearchActivity : BaseActivity<SearchViewModel, ActivitySearchBinding>() {


    override val viewModel by viewModel<SearchViewModel>()
    override fun getViewBinding() =
        ActivitySearchBinding.inflate(layoutInflater)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.imgBack.setOnClickListener {
            finish()
        }

    }

    override fun observeData() {

    }
}