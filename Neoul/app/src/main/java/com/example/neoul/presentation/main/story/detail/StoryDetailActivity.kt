package com.example.neoul.presentation.main.story.detail

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import com.example.neoul.adapter.BrandItemRVAdapter
import com.example.neoul.data.model.Story
import com.example.neoul.databinding.ActivityStoryDetailBinding
import com.example.neoul.presentation.BaseActivity
import com.example.neoul.presentation.main.brand.detail.BrandDetailActivity
import com.example.neoul.presentation.main.story.StoryFragment
import com.example.neoul.presentation.product.ProductActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class StoryDetailActivity : BaseActivity<StoryDetailViewModel, ActivityStoryDetailBinding>() {

    override val viewModel by viewModel<StoryDetailViewModel>{
        parametersOf(
            intent.getParcelableExtra<Story>(StoryFragment.STORY_KEY)
        )
    }

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
            is StoryDetailState.Loading->{
                handleLoading()
            }
            is StoryDetailState.Success->{
                handleSuccess(it)
            }
            else -> Unit
        }
    }

    override fun initViews(){
        binding.brandRecommendItem.adapter = adapter
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun handleLoading(){}

    private fun handleSuccess(state : StoryDetailState.Success){
        title = state.story.title
        binding.writerName.text = state.story.author
        binding.writeDate.text = state.story.date
        binding.articleContent.text = state.story.content
        adapter.setList(state.brandList)
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