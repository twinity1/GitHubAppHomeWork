package com.example.githubhomework.ui.recentrepositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.githubhomework.R
import com.example.githubhomework.databinding.FragmentRecentRepositoriesBinding
import com.example.githubhomework.persistence.entities.Repository
import org.koin.android.ext.android.inject

class RecentRepositoriesFragment : Fragment() {
    private lateinit var binding: FragmentRecentRepositoriesBinding
    private val viewModel: RecentRepositoriesViewModel by inject()

    private lateinit var gridView: GridView

    var title = ""

    var repositoryList: List<Repository> = listOf()
        set(value) {
            field = value
            setAdapter()
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recent_repositories, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        gridView = binding.root.findViewById(R.id.recentRepositoriesGridView)
        setAdapter()

        return binding.root
    }

    private fun setAdapter() {
        if (::gridView.isInitialized) {
            gridView.adapter = GridViewAdapter(requireContext(), repositoryList)
        }
    }
}