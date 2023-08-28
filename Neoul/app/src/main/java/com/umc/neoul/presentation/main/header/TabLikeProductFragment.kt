package com.umc.neoul.presentation.main.header

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.neoul.data.response.like.product.LikedProduct
import com.umc.neoul.data.response.like.product.dataToProduct
import com.umc.neoul.databinding.FragmentTabLikeProductBinding
import com.umc.neoul.presentation.BaseFragment
import com.umc.neoul.presentation.main.category.LikeListProductRVAdater
import com.umc.neoul.presentation.product.ProductActivity
import org.koin.android.viewmodel.ext.android.viewModel

class TabLikeProductFragment : BaseFragment<LikeListViewModel, FragmentTabLikeProductBinding>() {
    override val viewModel by viewModel<LikeListViewModel>()

    override fun getViewBinding() = FragmentTabLikeProductBinding.inflate(layoutInflater)

    override fun observeDate() = viewModel.likedProductLiveData.observe(viewLifecycleOwner) {
        productAdapter(it.likedProduct)
        binding.textProductCnt.text = "상품 " + it.productCnt.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabLikeProductBinding.inflate(layoutInflater)

        return binding.root
    }

    private fun productAdapter(itemList: List<LikedProduct>) {
        val dataRVAdapter = LikeListProductRVAdater(itemList, { data ->
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


}