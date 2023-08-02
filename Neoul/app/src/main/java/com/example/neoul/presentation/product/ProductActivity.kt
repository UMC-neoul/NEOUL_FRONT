package com.example.neoul.presentation.product

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.example.neoul.R
import com.example.neoul.data.model.GoodsItem
import com.example.neoul.data.model.Story
import com.example.neoul.databinding.ActivityProductBinding
import com.example.neoul.presentation.BaseActivity
import com.example.neoul.presentation.main.story.StoryFragment
import com.example.neoul.presentation.main.story.detail.StoryDetailState
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProductActivity : BaseActivity<ProductViewModel, ActivityProductBinding>() {

    override val viewModel: ProductViewModel by viewModel<ProductViewModel>() {
        parametersOf(
            intent.getParcelableExtra<Story>(PRODUCT_KEY)
        )
    }

    override fun getViewBinding(): ActivityProductBinding =
        ActivityProductBinding.inflate(layoutInflater)

    @SuppressLint("UseCompatTextViewDrawableApis")
    override fun observeData() {
        viewModel.productStateLiveData.observe(this) {
            when (it) {
                is ProductState.Success -> {
                    handleSuccess(it)
                }

                else -> Unit
            }
        }
        //찜 버튼 state
        viewModel.productLikedLiveData.observe(this) {
            when (it) {
                false -> {
                    binding.favoriteBtn.backgroundTintList =
                        ContextCompat.getColorStateList(this, R.color.primary60)
                    binding.favoriteText.apply {
                        setTextColor(resources.getColor(R.color.white))
                        text = "찜하기"
                    }
                    binding.favoriteText.compoundDrawableTintList =
                        ContextCompat.getColorStateList(this, R.color.white)
                }

                true -> {
                    binding.favoriteBtn.backgroundTintList =
                        ContextCompat.getColorStateList(this, R.color.primary95)
                    binding.favoriteText.apply {
                        setTextColor(resources.getColor(R.color.primary60))
                        text = "찜한 상품"
                    }
                    binding.favoriteText.compoundDrawableTintList =
                        ContextCompat.getColorStateList(this, R.color.primary60)

                }
            }
        }
    }

    private fun handleSuccess(state: ProductState.Success) {
        title = state.product.title
    }

    override fun initViews() {
        super.initViews()
        //toolbar 설정
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.close)

        binding.favoriteBtn.setOnClickListener {
            viewModel.clickLikeBtn()
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
        const val PRODUCT_KEY = "product"
        fun newIntent(context: Context, product: GoodsItem) =
            Intent(context, ProductActivity::class.java).apply {
                putExtra(PRODUCT_KEY, product)
            }

    }
}