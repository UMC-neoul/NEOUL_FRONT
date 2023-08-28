package com.umc.neoul.presentation.main.brand.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.umc.neoul.R
import com.umc.neoul.adapter.ProductGridRVAdapter
import com.umc.neoul.adapter.ProductHorizontalRVAdapter
import com.umc.neoul.data.model.BrandItem
import com.umc.neoul.databinding.ActivityBrandDetailBinding
import com.umc.neoul.presentation.BaseActivity
import com.umc.neoul.presentation.main.header.SearchActivity
import com.umc.neoul.presentation.main.MainMenuId
import com.umc.neoul.presentation.main.header.LikeListActivity
import com.umc.neoul.presentation.product.ProductActivity
import com.umc.neoul.util.MainMenuBus
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class BrandDetailActivity : BaseActivity<BrandDetailViewModel, ActivityBrandDetailBinding>() {

    override val viewModel by viewModel<BrandDetailViewModel> {
        parametersOf(
            intent.getParcelableExtra(BRAND_KEY)
        )
    }
    var hashTag = ""

    private val mainMenuBus by inject<MainMenuBus>()

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

                is BrandDetailState.Failure -> {
                    handleFailure()
                }

                is BrandDetailState.NotAuth -> {
                    handleNotAuth()
                }

                else -> Unit
            }
        }
        //정렬 순서 변화
        viewModel.productListLiveData.observe(this) {
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
        title = state.brand.name
        binding.brandSimpleDescription.text = state.brand.content
        Glide.with(this)
            .load(state.brand.image)
            .error(R.drawable.base_img)
            .fallback(R.drawable.base_img)
            .into(binding.brandLogo)
        adapterGrid.setList(state.brand.productList)
        adapterGrid.brandName = state.brand.name
        adapterHorizontal.setList(state.brand.productList)
        hashTag = ("#" + state.brand.hashTag?.joinToString(" #")) ?: " "
        binding.brandTag.text = hashTag
    }

    private fun handleNotAuth() {
        Toast.makeText(this, "로그인이 필요한 서비스입니다.", Toast.LENGTH_LONG).show()
        lifecycleScope.launch {
            mainMenuBus.changMenu(MainMenuId.My)
            finish()
        }
    }

    private fun handleFailure() {
        Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show()
    }

    override fun initViews() {
        super.initViews()
        setSupportActionBar(binding.toolBar)
        title = ""
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
                        viewModel.recommendSortClick()
                    }

                    1 -> {
                        binding.brandSortBtn.text = items[position]
                        lifecycleScope.launch {
                            viewModel.recentSortClick()
                        }
                    }

                    2 -> {
                        binding.brandSortBtn.text = items[position]
                        viewModel.lowPriceSortClick()
                    }

                    3 -> {
                        binding.brandSortBtn.text = items[position]
                        viewModel.highPriceSortClick()
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
                //뒤로가기
                finish()
                return true
            }

            R.id.toolbar_search -> {
                //검색화면 이동
                startActivity(Intent(this, SearchActivity::class.java))
                return true
            }

            R.id.toolbar_favorite -> {
                //찜화면 이동
                startActivity(Intent(this, LikeListActivity::class.java))
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