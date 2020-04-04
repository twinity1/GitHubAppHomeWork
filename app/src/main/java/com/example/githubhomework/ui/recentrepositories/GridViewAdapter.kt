package com.example.githubhomework.ui.recentrepositories

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import com.example.githubhomework.R
import com.example.githubhomework.databinding.GridItemRecentRepositoriesBinding
import com.example.githubhomework.persistence.entities.Repository

class GridViewAdapter(private val context: Context, private val repositories: List<Repository>) : BaseAdapter() {
    var onRepositorySelect: ((Repository) -> Unit)? = null

    private val viewBindings = ArrayList<GridItemRecentRepositoriesBinding>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        if (viewBindings.getOrNull(position) == null) {
            val binding: GridItemRecentRepositoriesBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.grid_item_recent_repositories, parent, false)
            val repository = repositories[position]

            val viewModel = RecentRepositoryViewModel()
            viewModel.repository.value = repository

            viewModel.onRepositorySelect = {
                onRepositorySelect?.invoke(repository)
            }

            binding.viewModel = viewModel
            viewBindings.add(binding)

            return binding.root
        }

        return viewBindings[position].root
    }

    override fun getItem(position: Int): Any {
        return repositories[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return repositories.count()
    }
}