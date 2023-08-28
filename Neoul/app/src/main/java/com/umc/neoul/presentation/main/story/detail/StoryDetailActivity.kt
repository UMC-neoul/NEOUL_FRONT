package com.umc.neoul.presentation.main.story.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.umc.neoul.R
import com.umc.neoul.adapter.BrandItemRVAdapter
import com.umc.neoul.data.model.Story
import com.umc.neoul.databinding.ActivityStoryDetailBinding
import com.umc.neoul.presentation.BaseActivity
import com.umc.neoul.presentation.main.MainActivity
import com.umc.neoul.presentation.main.MainMenuId
import com.umc.neoul.presentation.main.brand.detail.BrandDetailActivity
import com.umc.neoul.presentation.main.story.StoryFragment
import com.umc.neoul.presentation.product.ProductActivity
import com.umc.neoul.util.MainMenuBus
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class StoryDetailActivity : BaseActivity<StoryDetailViewModel, ActivityStoryDetailBinding>() {

    override val viewModel by viewModel<StoryDetailViewModel>{
        parametersOf(
            intent.getParcelableExtra<Story>(StoryFragment.STORY_KEY)
        )
    }

    private val mainMenuBus by inject<MainMenuBus>()

    private val adapter by lazy {
        BrandItemRVAdapter(
            brandClickListener = {
                startActivity(
                    BrandDetailActivity.newIntent(this,it)
                )
            },
            productClickListener = {
                startActivity(
                    ProductActivity.newIntent(this,it)
                )
            }
        )
    }

    override fun getViewBinding(): ActivityStoryDetailBinding = ActivityStoryDetailBinding.inflate(layoutInflater)
    override fun observeData() =viewModel.storyDetailLiveData.observe(this){
        when(it){
            is StoryDetailState.Success->{
                handleSuccess(it)
            }
            is StoryDetailState.NotAuth ->{
                handleNotAuth()
            }
            else -> Unit
        }
    }

    override fun initViews(){
        binding.brandRecommendItem.adapter = adapter
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = ""
        binding.seeMoreBtn.setOnClickListener {
            lifecycleScope.launch {
                mainMenuBus.changMenu(MainMenuId.BRAND)
                finish()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun handleSuccess(state : StoryDetailState.Success){
      //  binding.writerName.text = state.story.author
        binding.writeDate.text = state.story.date
        binding.articleContent.text = state.content
        binding.brandAdText.text = state.category+ "을(를) 후원하는 브랜드"
        adapter.setList(state.brandList)
        title = state.story.title
        Glide.with(this)
            .load(state.story.image)
            .error(R.drawable.base_img)
            .fallback(R.drawable.base_img)
            .into(binding.storyImage)
    }

    private fun handleNotAuth() {
        Toast.makeText(this, "로그인이 필요한 서비스입니다.", Toast.LENGTH_LONG).show()
        lifecycleScope.launch {
            mainMenuBus.changMenu(MainMenuId.My)
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object{
        fun newIntent(context: Context, story: Story) =
            Intent(context, StoryDetailActivity::class.java).apply {
                putExtra(StoryFragment.STORY_KEY, story)
            }
    }
}