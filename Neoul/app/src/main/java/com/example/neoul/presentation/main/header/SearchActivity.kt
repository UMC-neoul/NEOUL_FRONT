package com.example.neoul.presentation.main.header

import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neoul.adapter.ProductHorizontalRVAdapter
import com.example.neoul.data.model.Product
import com.example.neoul.data.response.product.recent.dataToProduct
import com.example.neoul.databinding.ActivitySearchBinding
import com.example.neoul.presentation.BaseActivity
import com.example.neoul.presentation.product.ProductActivity
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

    override fun observeData() = viewModel.recentLiveData.observe(this) {
        val product = it.map { dataToProduct(it) }
        productAdapter(product)
    }

    private fun productAdapter(itemList: List<Product>) {
        val dataRVAdapter = ProductHorizontalRVAdapter({ product ->
            startActivity(
                ProductActivity.newIntent(this, product)
            )
        }, itemList)

        binding.recyclerProduct.adapter = dataRVAdapter

        binding.recyclerProduct.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        dataRVAdapter.notifyDataSetChanged()
        binding.recyclerProduct.setHasFixedSize(true)
    }

}