package com.example.githubhomework.components.lists.repositories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubhomework.R
import com.example.githubhomework.databinding.RepositoryListBinding
import com.example.githubhomework.persistence.entities.Repository

class RepositoryListAdapter(private val repositories: List<Repository>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var layoutInflater: LayoutInflater

    var onShowRepository: ((RepositoryListViewModel) -> Unit)? = null

    private class RepositoryViewHolder(itemView: View, var binding: RepositoryListBinding) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (::layoutInflater.isInitialized == false) {
            layoutInflater = LayoutInflater.from(parent.context)
        }

        val binding = DataBindingUtil.inflate<RepositoryListBinding>(layoutInflater, R.layout.repository_list, parent, false);

        return RepositoryViewHolder(
            binding.root,
            binding
        )
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holder = holder as RepositoryViewHolder

        val viewModel = RepositoryListViewModel(repositories[position])

        holder.binding.viewModel = viewModel

        viewModel.onRepositoryShow = {
            onShowRepository?.invoke(viewModel)
        }
    }
}