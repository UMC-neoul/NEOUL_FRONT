package com.example.neoul.presentation.main

import android.os.Build.VERSION_CODES.P
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.example.neoul.R
import com.example.neoul.databinding.ActivityMainBinding
import com.example.neoul.presentation.main.brand.BrandFragment
import com.example.neoul.presentation.main.category.CategoryFragment
import com.example.neoul.presentation.main.home.HomeFragment
import com.example.neoul.presentation.main.my.MyFragment
import com.example.neoul.presentation.main.story.StoryFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews(){
        showFragment(HomeFragment.newInstance(),HomeFragment.TAG)

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_home->{
                    showFragment(HomeFragment.newInstance(),HomeFragment.TAG)
                    return@setOnItemSelectedListener true
                }
                R.id.menu_category->{
                    showFragment(CategoryFragment.newInstance(), CategoryFragment.TAG)
                    return@setOnItemSelectedListener true
                }
                R.id.menu_brand->{
                    showFragment(BrandFragment.newInstance(), BrandFragment.TAG)
                    return@setOnItemSelectedListener true
                }
                R.id.menu_story->{
                    showFragment(StoryFragment.newInstance(), StoryFragment.TAG)
                    return@setOnItemSelectedListener true
                }
                R.id.menu_my ->{
                    showFragment(MyFragment.newInstance(), MyFragment.TAG)
                    return@setOnItemSelectedListener true
                }
                else ->{
                    return@setOnItemSelectedListener false
                }
            }
        }
    }

    private fun showFragment(fragment: Fragment, tag:String){
        val findFragment = supportFragmentManager.findFragmentByTag(tag)
        supportFragmentManager.fragments.forEach {
            supportFragmentManager.beginTransaction().hide(it).commitAllowingStateLoss()
        }
        findFragment?.let {
            supportFragmentManager.beginTransaction().show(it).commitAllowingStateLoss()
        } ?: kotlin.run {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, fragment, tag)
                .commitAllowingStateLoss()
        }
    }
}