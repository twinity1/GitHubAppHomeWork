package com.example.githubhomework.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.githubhomework.R
import com.example.githubhomework.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    lateinit var homeViewModel: HomeViewModel
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.viewModel = homeViewModel
        binding.lifecycleOwner = this

        val viewPager = binding.root.findViewById<ViewPager2>(R.id.recentRepositoriesViewPager)
        val tabs = binding.root.findViewById<TabLayout>(R.id.recentRepositoriesTab)


        val viewPagerAdapter = ViewPagerAdapter(requireActivity())
        viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(tabs, viewPager, object : TabLayoutMediator.TabConfigurationStrategy {
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                tab.setText("xxx ${position}")
            }
        }).attach()

        return binding.root
    }
}
