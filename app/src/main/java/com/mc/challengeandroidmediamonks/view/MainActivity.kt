package com.mc.challengeandroidmediamonks.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.mc.challengeandroidmediamonks.R
import com.mc.challengeandroidmediamonks.databinding.ActivityMainBinding
import com.mc.challengeandroidmediamonks.view.fragment.AlbumListFragment
import com.mc.challengeandroidmediamonks.view.fragment.PhotoListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFragmentsParts()

    }

    private fun initFragmentsParts() {
        binding.viewPager.adapter = FragmentAdapter(this)
        TabLayoutMediator(binding.tabLayout,binding.viewPager){tab, position ->
            if(position==0)
                tab.text = getString(R.string.album)
            else
                tab.text = getString(R.string.photo)
        }.attach()
    }

    fun changeTab(){
        binding.viewPager.currentItem = binding.viewPager.currentItem+1
    }

    class FragmentAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity){

        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return if(position==0)
                AlbumListFragment.newInstance()
            else
                PhotoListFragment.newInstance()
        }

    }

}
