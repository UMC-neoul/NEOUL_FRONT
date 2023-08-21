package com.example.neoul.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.neoul.R
import com.example.neoul.databinding.ActivityMainBinding
import com.example.neoul.presentation.main.brand.BrandFragment
import com.example.neoul.presentation.main.category.CategoryFragment
import com.example.neoul.presentation.main.home.HomeFragment
import com.example.neoul.presentation.main.my.MyPageFragment
import com.example.neoul.presentation.main.story.StoryFragment
import com.example.neoul.util.MainMenuBus
import com.example.neoul.util.NetworkManager
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    private val menuBus by inject<MainMenuBus>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        navController = binding.navHostFragment.findNavController()
        initViews()
        observe()
    }

    private fun initViews() {
        //네트워크 연결 상태 확인
        if (NetworkManager.checkNetworkState(this)){
            binding.bottomNav.isVisible = true
            binding.navHostFragment.isVisible = true
            showFragment(HomeFragment.newInstance(), HomeFragment.TAG)


            binding.bottomNav.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_home -> {

                        it.setIcon(R.drawable.home_after)
                        showFragment(HomeFragment.newInstance(), HomeFragment.TAG)
                        return@setOnItemSelectedListener true
                    }

                    R.id.menu_category -> {
                        binding.bottomNav.menu.findItem(R.id.menu_home).setIcon(R.drawable.home)
                        showFragment(CategoryFragment.newInstance(), CategoryFragment.TAG)
                        return@setOnItemSelectedListener true
                    }

                    R.id.menu_brand -> {
                        binding.bottomNav.menu.findItem(R.id.menu_home).setIcon(R.drawable.home)
                        showFragment(BrandFragment.newInstance(), BrandFragment.TAG)
                        return@setOnItemSelectedListener true
                    }

                    R.id.menu_story -> {
                        binding.bottomNav.menu.findItem(R.id.menu_home).setIcon(R.drawable.home)
                        showFragment(StoryFragment.newInstance(), StoryFragment.TAG)
                        return@setOnItemSelectedListener true
                    }

                    R.id.menu_my -> {
                        binding.bottomNav.menu.findItem(R.id.menu_home).setIcon(R.drawable.home)
                        showFragment(MyPageFragment.newInstance(), MyPageFragment.TAG)
                        return@setOnItemSelectedListener true
                    }

                    else -> {
                        return@setOnItemSelectedListener false
                    }
                }
            }

        }else{
            Toast.makeText(this,"네트워크 연결을 확인해 주세요", Toast.LENGTH_LONG).show()
            binding.bottomNav.isGone = true
            binding.navHostFragment.isGone = true
        }
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        val findFragment = supportFragmentManager.findFragmentByTag(tag)
        supportFragmentManager.fragments.forEach {
            supportFragmentManager.beginTransaction().hide(it).commitAllowingStateLoss()
        }
        findFragment?.let {
            supportFragmentManager.beginTransaction().show(it).commitAllowingStateLoss()
        } ?: kotlin.run {
            supportFragmentManager.beginTransaction()
                .add(R.id.nav_host_fragment, fragment, tag)
                .commitAllowingStateLoss()
        }
    }
    fun goToTab(menuId: MainMenuId){
        binding.bottomNav.selectedItemId = menuId.id
    }

    private fun observe(){
        lifecycleScope.launch {
            menuBus.mainTabFlow.collect{
                goToTab(it)
            }
        }
    }
}

enum class MainMenuId(@IdRes val id: Int){
    BRAND(R.id.menu_brand),My(R.id.menu_my)
}