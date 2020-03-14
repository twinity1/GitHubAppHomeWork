package com.example.githubhomework.components.lists.issues

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubhomework.R
import com.example.githubhomework.databinding.FragmentIssuesListBinding
import com.example.githubhomework.entities.GitHubRepositoryIssue

class RepositoryIssuesListAdapter(private val issues: List<GitHubRepositoryIssue>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var layoutInflater: LayoutInflater

    var onShowRepository: ((RepositoryIssueListViewModel) -> Unit)? = null

    private class ViewHolder(itemView: View, var binding: FragmentIssuesListBinding) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (::layoutInflater.isInitialized == false) {
            layoutInflater = LayoutInflater.from(parent.context)
        }

        val binding = DataBindingUtil.inflate<FragmentIssuesListBinding>(layoutInflater, R.layout.fragment_issues_list, parent, false);

        return ViewHolder(
            binding.root,
            binding
        )
    }

    override fun getItemCount(): Int {
        return issues.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holder = holder as ViewHolder

        val viewModel = RepositoryIssueListViewModel(issues[position])

        holder.binding.viewModel = viewModel
    }
}