package com.example.neoul.presentation.main.header

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neoul.adapter.BrandCardRVAdapter
import com.example.neoul.adapter.ProductGridRVAdapter
import com.example.neoul.adapter.ProductHorizontalRVAdapter
import com.example.neoul.data.model.Product
import com.example.neoul.data.response.product.recent.dataToProduct
import com.example.neoul.databinding.ActivitySearchBinding
import com.example.neoul.presentation.BaseActivity
import com.example.neoul.presentation.main.MainMenuId
import com.example.neoul.presentation.main.brand.detail.BrandDetailActivity
import com.example.neoul.presentation.product.ProductActivity
import com.example.neoul.util.MainMenuBus
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class SearchActivity : BaseActivity<SearchViewModel, ActivitySearchBinding>() {


    override val viewModel by viewModel<SearchViewModel>()
    override fun getViewBinding() =
        ActivitySearchBinding.inflate(layoutInflater)

    private val adapterBrand by lazy {
        BrandCardRVAdapter(
            brandClickListener = {
                startActivity(
                    BrandDetailActivity.newIntent(this, it)
                )
            })
    }

    private val adapterGrid by lazy {
        ProductGridRVAdapter(
            productClickListener = {
                startActivity(
                    ProductActivity.newIntent(this, it)
                )
            }
        )
    }

    private val mainMenuBus by inject<MainMenuBus>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.imgBack.setOnClickListener {
            finish()
        }

        binding.editSearch.setOnKeyListener { v, keyCode, event ->
            if ((event.action== KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                viewModel.search(binding.editSearch.text.toString())
                binding.editSearch.setText("")
                true
            } else {
                false
            }
        }
        binding.recyclerBrandSearch.adapter = adapterBrand

        //상품은 데이터가 너무 많아서 api 없으면 불가능
        binding.recyclerProductSearch.adapter = adapterGrid
    }


    override fun observeData() {
        viewModel.recentLiveData.observe(this) {
            val product = it.map { dataToProduct(it) }
            productAdapter(product)
        }
        viewModel.searchStateLiveData.observe(this) {
            when (it) {
                is SearchState.SearchBefore -> handleBefore()
                is SearchState.SearchAfter -> handleAfter(it.brandSize, it.productSize)
                is SearchState.Failure -> Unit
                is SearchState.NoAuth -> handleNoAuth()
            }
        }
        viewModel.searchBrandLiveData.observe(this) {
            adapterBrand.setList(it)
        }
        viewModel.searchProductLiveData.observe(this) {
            adapterGrid.setList(it)
        }
    }

    private fun handleNoAuth() {
        Toast.makeText(this, "로그인이 필요한 서비스입니다.", Toast.LENGTH_LONG).show()
        lifecycleScope.launch {
            mainMenuBus.changMenu(MainMenuId.My)
            finish()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun handleAfter(brandSize : Int, productSize: Int) {
        binding.searchBefore.isGone = true
        binding.searchAfter.isVisible = true
        binding.textBrandSearch.text = "브랜드 $brandSize"
        binding.textProductSearch.text = "상품 $productSize"
    }

    private fun handleBefore() {
        binding.searchBefore.isVisible = true
        binding.searchAfter.isGone = true
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