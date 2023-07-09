package com.example.neoul.presentation.main.brand

import android.content.Intent
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.view.isVisible
import com.example.neoul.R
import com.example.neoul.databinding.FragmentBrandBinding
import com.example.neoul.presentation.BaseFragment
import com.example.neoul.presentation.main.brand.detail.BrandDetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class BrandFragment : BaseFragment<BrandViewModel, FragmentBrandBinding>() {

    override val viewModel by viewModel<BrandViewModel>()

    override fun getViewBinding() = FragmentBrandBinding.inflate(layoutInflater)

    override fun initViews() {
        super.initViews()

        //
        binding.banner.setOnClickListener {
            startActivity(Intent(requireContext(),BrandDetailActivity::class.java))
        }

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
                    }

                    1 -> {
                        binding.brandSortBtn.text = items[1]
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
        binding.brandSortBtn.setOnClickListener {
            binding.brandSpinner.performClick()
        }
    }

    override fun observeDate() {}

    companion object {
        fun newInstance() = BrandFragment()

        const val TAG = "BrandFragment"
    }
}