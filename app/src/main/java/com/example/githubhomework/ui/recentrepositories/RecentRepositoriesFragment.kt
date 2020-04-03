package com.example.githubhomework.ui.recentrepositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.githubhomework.R
import com.example.githubhomework.databinding.FragmentRecentRepositoriesBinding
import org.koin.android.ext.android.inject

class RecentRepositoriesFragment : Fragment() {
    private lateinit var binding: FragmentRecentRepositoriesBinding
    private val viewModel: RecentRepositoriesViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recent_repositories, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}