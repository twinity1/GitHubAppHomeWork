package com.example.githubhomework.ui.recentrepositories

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.githubhomework.R
import com.example.githubhomework.RepositoryActivity
import com.example.githubhomework.databinding.FragmentRecentRepositoriesBinding
import com.example.githubhomework.persistence.entities.Repository
import org.koin.android.ext.android.inject

class RecentRepositoriesFragment : Fragment() {
    private lateinit var binding: FragmentRecentRepositoriesBinding
    val viewModel: RecentRepositoriesViewModel by inject()

    lateinit var gridView: GridView

    var title = ""

    var refreshHandler = {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recent_repositories, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.repositories.observe(viewLifecycleOwner, RepositoriesObserver().create(this))

        gridView = binding.root.findViewById(R.id.recentRepositoriesGridView)

        return binding.root
    }

    fun isGridViewInitialized(): Boolean {
        return ::gridView.isInitialized
    }

    fun refresh() {
        if (isGridViewInitialized()) {
            val gridViewAdapter = gridView.adapter as GridViewAdapter
            gridViewAdapter.notifyDataSetChanged()
            gridView.invalidate()
        }
    }
}