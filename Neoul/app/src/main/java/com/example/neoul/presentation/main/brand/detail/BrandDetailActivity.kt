package com.example.neoul.presentation.main.brand.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import com.example.neoul.R
import com.example.neoul.adapter.ProductGridRVAdapter
import com.example.neoul.adapter.ProductHorizontalRVAdapter
import com.example.neoul.data.model.BrandItem
import com.example.neoul.databinding.ActivityBrandDetailBinding
import com.example.neoul.presentation.BaseActivity
import com.example.neoul.presentation.product.ProductActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class BrandDetailActivity : BaseActivity<BrandDetailViewModel, ActivityBrandDetailBinding>() {

    override val viewModel by viewModel<BrandDetailViewModel> {
        parametersOf(
            intent.getParcelableExtra(BRAND_KEY)
        )
    }

    override fun getViewBinding(): ActivityBrandDetailBinding =
        ActivityBrandDetailBinding.inflate(layoutInflater)

    private val adapterHorizontal by lazy {
        ProductHorizontalRVAdapter(
            productClickListener = {
                startActivity(
                    ProductActivity.newIntent(this, it)
                )
            }
        )
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

    @SuppressLint("UseCompatTextViewDrawableApis")
    override fun observeData() {
        viewModel.brandDetailStateLiveData.observe(this) {
            when (it) {
                is BrandDetailState.Success -> {
                    handleSuccess(it)
                }
                else -> Unit
            }
        }
        //정렬 순서 변화
        viewModel.productListLiveData.observe(this){
            adapterGrid.setList(it)
        }
        //찜 상태 변화
        viewModel.brandLikedLiveData.observe(this) {
            when (it) {
                false -> {
                    binding.favoriteBtn.backgroundTintList =
                        ContextCompat.getColorStateList(this, R.color.primary60)
                    binding.favoriteText.apply {
                        setTextColor(resources.getColor(R.color.white))
                        text = "브랜드 찜하기"
                    }
                    binding.favoriteText.compoundDrawableTintList =
                        ContextCompat.getColorStateList(this, R.color.white)
                }

                true -> {
                    binding.favoriteBtn.backgroundTintList =
                        ContextCompat.getColorStateList(this, R.color.primary95)
                    binding.favoriteText.apply {
                        setTextColor(resources.getColor(R.color.primary60))
                        text = "찜한 브랜드"
                    }
                    binding.favoriteText.compoundDrawableTintList =
                        ContextCompat.getColorStateList(this, R.color.primary60)
                }
            }
        }
    }

    private fun handleSuccess(state: BrandDetailState.Success) {
        title = state.product.name
        binding.brandSimpleDescription.text = state.product.content
        adapterGrid.setList(state.product.productList)
        adapterHorizontal.setList(state.product.productList)
    }

    override fun initViews() {
        super.initViews()
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.brandRecommendItem.adapter = adapterHorizontal
        binding.itemRecyclerView.adapter = adapterGrid
        binding.favoriteBtn.setOnClickListener {
            viewModel.clickLikeBtn()
        }
        spinnerAdapt()
    }

    //정렬 버튼 설정
    private fun spinnerAdapt() {
        val items = resources.getStringArray(R.array.product_sort)
        val arrayAdapter = ArrayAdapter(this, R.layout.item_spinner, items)
        binding.spinner.adapter = arrayAdapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        binding.brandSortBtn.text = items[position]
                    }

                    1 -> {
                        binding.brandSortBtn.text = items[position]
                    }

                    2 -> {
                        binding.brandSortBtn.text = items[position]
                    }

                    3 -> {
                        binding.brandSortBtn.text = items[position]
                    }
                    4 -> {
                        binding.brandSortBtn.text = items[position]
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        binding.brandSortBtn.setOnClickListener {
            binding.spinner.performClick()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.toolbar_search -> {
                //검색화면 이동
                return true
            }
            R.id.toolbar_favorite -> {
                //찜화면 이동
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val BRAND_KEY = "brand"
        fun newIntent(context: Context, brandItem: BrandItem) =
            Intent(context, BrandDetailActivity::class.java).apply {
                putExtra(BRAND_KEY, brandItem)
            }

    }
}