package com.umc.neoul.presentation.main.category

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.umc.neoul.R
import com.umc.neoul.databinding.FragmentCategoryBinding
import com.umc.neoul.presentation.BaseFragment
import com.umc.neoul.presentation.main.brand.BrandFragment
import com.umc.neoul.presentation.main.header.LikeListActivity
import com.umc.neoul.presentation.main.header.SearchActivity
import com.umc.neoul.util.CategoryMenuBus
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CategoryFragment : BaseFragment<CategoryViewModel, FragmentCategoryBinding>() {

    override val viewModel by viewModel<CategoryViewModel> {
        parametersOf(1, 1)
    }

    private val categoryMenuBus by inject<CategoryMenuBus>()

    override fun getViewBinding() = FragmentCategoryBinding.inflate(layoutInflater)

    override fun observeDate() = viewModel.categoryLiveData.observe(viewLifecycleOwner) {


    }

    companion object {
        fun newInstance() = CategoryFragment()

        const val TAG = "CategoryFragment"
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(layoutInflater)
        binding.imgSearch.setOnClickListener {
            startActivity(Intent(requireContext(), SearchActivity::class.java))

        }
        binding.imgHeart.setOnClickListener {
            startActivity(Intent(requireContext(), LikeListActivity::class.java))

        }
        val tabAdapter = TabAdapter(this)
        binding.viewPager.adapter = tabAdapter

        val tabTitleArray = arrayOf(
            "의류",
            "소품",
            "악세사리",
            "잡화",
            "기타"
        )

        TabLayoutMediator(binding.layoutTab, binding.viewPager) { tab, position ->
            tab.text = tabTitleArray[position]

        }.attach()

        observeMenuBus()

        return binding.root
    }

    fun goToTab( menu : CategoryMenu){
        binding.viewPager.currentItem = menu.position
    }

    fun observeMenuBus(){
        lifecycleScope.launch {
            categoryMenuBus.category.collect{
                goToTab(it)
            }
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            viewModel.fetchData()
        }
    }

}

enum class CategoryMenu(val position:Int){
    CLOTHES(0),PROP(1),ACC(2),STUFF(3),ETC(4)
}