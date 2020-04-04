package com.example.githubhomework.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubhomework.ui.recentrepositories.RecentRepositoriesFragment
import org.koin.ext.getFullName

class ViewPagerAdapter(private val fragments: List<Fragment>, private val fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return fragments.count()
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}