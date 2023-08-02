package com.example.neoul.presentation.main.my

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.neoul.databinding.FragmentMypageBinding
import com.example.neoul.presentation.BaseFragment
import com.example.neoul.presentation.main.my.setting.MyPageSettingActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MyPageFragment : BaseFragment<MyPageViewModel, FragmentMypageBinding>() {

    override val viewModel by viewModel<MyPageViewModel>()

    override fun getViewBinding() = FragmentMypageBinding.inflate(layoutInflater)

    override fun observeDate() {}

    companion object {
        fun newInstance() = MyPageFragment()

        const val TAG = "MyFragment"
    }

    private lateinit var viewBinding:FragmentMypageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMypageBinding.inflate(layoutInflater)

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding){

            setting.setOnClickListener {
                startActivity(Intent(requireContext(),MyPageSettingActivity::class.java))

            }

        }//with(viewbinding)



    }
}