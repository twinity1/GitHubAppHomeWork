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
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    private val tabFragmentsFactory: TabFragmentsFactory by inject()

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.viewModel = homeViewModel
        binding.lifecycleOwner = this

        viewPager = binding.root.findViewById(R.id.recentRepositoriesViewPager)
        tabLayout = binding.root.findViewById(R.id.recentRepositoriesTab)

        configureView()

        return binding.root
    }

    private fun configureView() {
        val fragments = tabFragmentsFactory.getFragments(this)

        val viewPagerAdapter = ViewPagerAdapter(fragments, requireActivity())
        viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(
            tabLayout,
            viewPager,
            object : TabLayoutMediator.TabConfigurationStrategy {
                override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                    tab.setText(fragments[position].title)
                }
            }).attach()
    }
}
