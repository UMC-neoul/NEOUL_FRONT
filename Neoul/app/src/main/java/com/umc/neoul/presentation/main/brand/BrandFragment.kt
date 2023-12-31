package com.umc.neoul.presentation.main.brand

import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.umc.neoul.R
import com.umc.neoul.adapter.BrandItemRVAdapter
import com.umc.neoul.databinding.FragmentBrandBinding
import com.umc.neoul.presentation.BaseFragment
import com.umc.neoul.presentation.main.brand.detail.BrandDetailActivity
import com.umc.neoul.presentation.main.header.LikeListActivity
import com.umc.neoul.presentation.main.header.SearchActivity
import com.umc.neoul.presentation.product.ProductActivity
import org.koin.android.viewmodel.ext.android.viewModel

class BrandFragment : BaseFragment<BrandViewModel, FragmentBrandBinding>() {

    companion object {
        fun newInstance() = BrandFragment()

        const val TAG = "BrandFragment"
    }

    override val viewModel by viewModel<BrandViewModel>()

    override fun getViewBinding() = FragmentBrandBinding.inflate(layoutInflater)

    private val adapter by lazy {
        BrandItemRVAdapter(
            brandClickListener = {
                startActivity(
                    BrandDetailActivity.newIntent(requireContext(), it)
                )
            }, productClickListener = {
                startActivity(
                    ProductActivity.newIntent(requireContext(),it)
                )
            })
    }

    override fun initViews() {
        super.initViews()
        spinnerAdapt()
        binding.recyclerView.adapter = adapter
        binding.searchBtn.setOnClickListener {
            startActivity(Intent(requireContext(), SearchActivity::class.java))
        }
        binding.likeListBtn.setOnClickListener {
            startActivity(Intent(requireContext(), LikeListActivity::class.java))
        }
    }

    override fun observeDate() = viewModel.brandLiveData.observe(viewLifecycleOwner) {
        adapter.setList(it)
    }

    private fun spinnerAdapt() {
        val items = resources.getStringArray(R.array.brand_sort)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.item_spinner, items)
        binding.brandSpinner.adapter = arrayAdapter
        binding.brandSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        binding.brandSortBtn.text = items[0]
                        viewModel.recommendSortClick()
                    }

                    1 -> {
                        binding.brandSortBtn.text = items[1]
                        viewModel.recentSortClick()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        binding.brandSortBtn.setOnClickListener {
            binding.brandSpinner.performClick()
        }
    }
}