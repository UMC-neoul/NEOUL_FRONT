package com.example.neoul.presentation.main.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neoul.R

import com.example.neoul.data.model.CategoryItem
import com.example.neoul.data.response.product.category.Data
import com.example.neoul.data.response.product.category.dataToProduct

import com.example.neoul.databinding.FragmentTabClothesBinding
import com.example.neoul.presentation.BaseFragment
import com.example.neoul.presentation.product.ProductActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class TabPropFragment : BaseFragment<CategoryViewModel, FragmentTabClothesBinding>() {
    //    private lateinit var viewBinding: FragmentTabClothesBinding
    private var option : Int = 1
    override val viewModel by viewModel<CategoryViewModel> {
        parametersOf(8)
    }

    override fun getViewBinding() = FragmentTabClothesBinding.inflate(layoutInflater)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabClothesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spinnerAdapt()

    }

    override fun observeDate() = viewModel.categoryLiveData.observe(viewLifecycleOwner) {
        categoryAdapter(it)
    }

    private fun categoryAdapter(itemList: List<Data>) {
        val dataRVAdapter = TabRVAdapter(itemList, { data ->
            val product = dataToProduct(data)
            startActivity(
                ProductActivity.newIntent(requireContext(), product)
            )
        },viewModel)


        binding.recyclerItem.adapter = dataRVAdapter
        binding.recyclerItem.layoutManager =
            GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        dataRVAdapter.notifyDataSetChanged()
        binding.recyclerItem.setHasFixedSize(true)
    }

    private fun spinnerAdapt() {
        val items = resources.getStringArray(R.array.category_sort)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.item_spinner, items)
        binding.brandSpinner.adapter = arrayAdapter
        binding.brandSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        binding.textFilter.text = items[0]
                        option = 1

                    }

                    1 -> {
                        binding.textFilter.text = items[1]
                        option = 2

                    }

                    2 -> {
                        binding.textFilter.text = items[2]
                        option = 3
                    }

                    3 -> {
                        binding.textFilter.text = items[3]
                        option = 4

                    }

                    4 -> {
                        binding.textFilter.text = items[4]
                        option = 5

                    }


                }
                viewModel.fetchData(option)

            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        binding.textFilter.setOnClickListener {
            binding.brandSpinner.performClick()
        }
    }


}