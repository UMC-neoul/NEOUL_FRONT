package com.example.neoul.presentation.main.my

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.neoul.data.model.Product
import com.example.neoul.data.repository.mypage.MyPageRepository
import com.example.neoul.databinding.FragmentMypageBinding
import com.example.neoul.presentation.BaseFragment
import com.example.neoul.presentation.main.brand.detail.BrandDetailActivity
import com.example.neoul.presentation.main.header.LikeListActivity
import com.example.neoul.presentation.main.header.SearchActivity
import com.example.neoul.presentation.main.home.EventActivity
import com.example.neoul.presentation.main.my.adapter.MyPageRVAdapter
import com.example.neoul.presentation.main.my.data.MyPageProduct
import com.example.neoul.presentation.main.my.setting.MyPageSettingActivity
import com.example.neoul.presentation.product.ProductActivity
import com.example.neoul.presentation.user.login.LoginActivity
import com.example.neoul.util.getJwt
import com.example.neoul.util.getSignName
import com.example.neoul.util.getUsername
import com.example.neoul.util.getkakaoLogin
import com.example.neoul.util.savePhone
import com.example.neoul.util.saveSignName
import com.example.neoul.util.saveUserBirth
import com.example.neoul.util.saveUsername
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.java.KoinJavaComponent.inject

class MyPageFragment : BaseFragment<MyPageViewModel, FragmentMypageBinding>() {

    override val viewModel by viewModel<MyPageViewModel>()

    override fun getViewBinding() = FragmentMypageBinding.inflate(layoutInflater)

    override fun observeDate() = viewModel.mypageLiveData.observe(viewLifecycleOwner) {
        adapter.setList(it)
    }

    companion object {
        fun newInstance() = MyPageFragment()

        const val TAG = "MyFragment"
    }

    private val myPageApi by inject<MyPageRepository>()

    private val adapter by lazy {
        MyPageRVAdapter(
            mypageclickListener = {
                startActivity(
                    ProductActivity.newIntent(requireContext(), it.getProduct())
                )
            }
        )
    }

    private fun MyPageProduct.getProduct() = Product(
        deliveryInfo = "",
        name = brandName,
        productId = productId,
        price = price,
        productImg = productImgList[0],
        productUrl = "",
    )


    override fun initViews() {
        super.initViews()
        spinnerAdapt()
        viewBinding.recycleMerchandise.adapter = adapter
    }

    private lateinit var viewBinding: FragmentMypageBinding


    private fun spinnerAdapt() {

    }

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

        mypagesetting()

        with(viewBinding) {

            setting.setOnClickListener {
                if (getJwt() != null) {
                    startActivity(Intent(requireContext(), MyPageSettingActivity::class.java))
                } else if (getkakaoLogin()!!) {
                    startActivity(Intent(requireContext(), MyPageSettingActivity::class.java))
                } else {
                    Toast.makeText(activity, "로그인 후 이용 가능합니다.", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            btnUserPrivacy.setOnClickListener {
                if (viewBinding.txtLogin.visibility == View.VISIBLE) {
                    startActivity(Intent(requireContext(), LoginActivity::class.java))
                    requireActivity().finish()
                }
            }

            search.setOnClickListener {
                startActivity(Intent(requireContext(), SearchActivity::class.java))
            }
            favorite.setOnClickListener {
                startActivity(Intent(requireContext(), LikeListActivity::class.java))
            }
            linearLayout7.setOnClickListener {
                startActivity(Intent(requireContext(), LikeListActivity::class.java))
            }
            notice.setOnClickListener {
                startActivity(Intent(requireContext(), EventActivity::class.java))
            }

        }//with(viewbinding)
    }

    private fun mypagesetting() {
        Log.d("Tester", "mypagesetting: 들어왔냐?")
        if (getkakaoLogin()!!) {
//            UserApiClient.instance.me{user, error ->
//                viewBinding.txtUserName.text = "${user?.kakaoAccount?.profile?.nickname}"
//                viewBinding.txtUserEmail.text = "${user?.kakaoAccount?.email}"
//                viewBinding.txtUserName.visibility = View.VISIBLE
//                viewBinding.txtUserEmail.visibility = View.INVISIBLE
//                viewBinding.txtLogin.visibility = View.GONE
//            }
        } else {
            viewBinding.txtUserName.visibility = View.GONE
            viewBinding.txtUserEmail.visibility = View.GONE
            viewBinding.txtLogin.visibility = View.VISIBLE
            viewBinding.loginImg.visibility = View.VISIBLE
        }

        if (getUsername() != null) {
            Log.d("Tester", "mypagesetting: 들어옴?")
            lifecycleScope.launch {
                val mypageinfodata = myPageApi.mypageinfo(getJwt()!!)
                savePhone(mypageinfodata!!.data.phone)
                saveUsername(mypageinfodata!!.data.name)
                saveSignName(mypageinfodata!!.data.username)
                saveUserBirth(mypageinfodata!!.data.birth)

                if (mypageinfodata!!.code == 200) {
                    viewBinding.txtUserName.text = mypageinfodata.data.name + "님"
                    viewBinding.txtUserEmail.text = mypageinfodata.data.username
                    viewBinding.txtUserName.visibility = View.VISIBLE
                    viewBinding.txtUserEmail.visibility = View.VISIBLE
                    viewBinding.txtLogin.visibility = View.GONE
                    viewBinding.loginImg.visibility = View.GONE

                } else {
                    viewBinding.txtUserName.visibility = View.GONE
                    viewBinding.txtUserEmail.visibility = View.GONE
                    viewBinding.txtLogin.visibility = View.VISIBLE
                    viewBinding.loginImg.visibility = View.VISIBLE
                }

            }
        } else {
            viewBinding.txtUserName.visibility = View.GONE
            viewBinding.txtUserEmail.visibility = View.GONE
            viewBinding.txtLogin.visibility = View.VISIBLE
            viewBinding.loginImg.visibility = View.VISIBLE
        }

    }

    //최근 조회 상품 조회 갱신
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            viewModel.fetchData()
        }
    }

}