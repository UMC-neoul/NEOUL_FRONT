package com.umc.neoul.presentation.product

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.umc.neoul.R
import com.umc.neoul.data.model.Product
import com.umc.neoul.data.model.Story
import com.umc.neoul.databinding.ActivityProductBinding
import com.umc.neoul.presentation.BaseActivity
import com.umc.neoul.presentation.main.MainMenuId
import com.umc.neoul.presentation.main.header.LikeListActivity
import com.umc.neoul.presentation.main.header.SearchActivity
import com.umc.neoul.util.MainMenuBus
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.text.SimpleDateFormat
import java.util.Date

class ProductActivity : BaseActivity<ProductViewModel, ActivityProductBinding>() {

    override val viewModel: ProductViewModel by viewModel<ProductViewModel>() {
        parametersOf(
            intent.getParcelableExtra<Story>(PRODUCT_KEY)
        )
    }
    private val mainMenuBus by inject<MainMenuBus>()
    override fun getViewBinding(): ActivityProductBinding =
        ActivityProductBinding.inflate(layoutInflater)

    @SuppressLint("UseCompatTextViewDrawableApis")
    override fun observeData() {
        viewModel.productStateLiveData.observe(this) {
            when (it) {
                is ProductState.Success -> {
                    handleSuccess(it)
                }

                is ProductState.Failure -> {
                    handleFailure()
                }

                is ProductState.NotAuth -> {
                    handleNotAuth()
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

    @SuppressLint("SetJavaScriptEnabled")
    private fun handleSuccess(state: ProductState.Success) {
        title = state.product.name
        //현재 시간 가져오기
        val now = System.currentTimeMillis()
        val date = Date(now)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val getTime = dateFormat.format(date)
        viewModel.postViewHistory(getTime)

        //webView 설정
        binding.webView.apply {
            this.settings.apply {
                javaScriptEnabled = true // 자바스크립트 사용여부
                setSupportMultipleWindows(true) // 새창 띄우기 허용여부
                javaScriptCanOpenWindowsAutomatically = true // 자바스크립트가 window.open()을 사용할 수 있도록 설정
                loadWithOverviewMode = true // html의 컨텐츠가 웹뷰보다 클 경우 스크린 크기에 맞게 조정
            }
            this.webViewClient = WebViewClient()    //웹 뷰 내에서 열기
            this.webChromeClient = WebChromeClient()
            this.loadUrl(state.product.productUrl)
        }
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
        const val PRODUCT_KEY = "product"
        fun newIntent(context: Context, product: Product) =
            Intent(context, ProductActivity::class.java).apply {
                putExtra(PRODUCT_KEY, product)
            }

    }
}