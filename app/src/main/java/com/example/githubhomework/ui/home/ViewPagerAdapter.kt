package com.example.githubhomework.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubhomework.ui.recentrepositories.RecentRepositoriesFragment
import org.koin.ext.getFullName

class ViewPagerAdapter(private val fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

//
//
//    override fun getItem(position: Int): Fragment {
//        return fragmentManager.fragmentFactory.instantiate(ClassLoader.getSystemClassLoader(), RecentRepositoriesFragment::class.getFullName())
//    }


//
//    override fun getCount(): Int {
//        return 2
//    }
//
//    override fun getPageTitle(position: Int): CharSequence? {
//        return "xxx ${position}"
//    }

    override fun getItemCount(): Int {
        return 2;
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentActivity.supportFragmentManager.fragmentFactory.instantiate(ClassLoader.getSystemClassLoader(), RecentRepositoriesFragment::class.getFullName())
    }
}