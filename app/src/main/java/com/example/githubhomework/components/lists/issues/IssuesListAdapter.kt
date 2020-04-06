package com.example.githubhomework.components.lists.issues

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubhomework.R
import com.example.githubhomework.databinding.FragmentIssuesListBinding
import com.example.githubhomework.persistence.entities.Issue
import com.example.githubhomework.tools.Identity.IdentityManager

class IssuesListAdapter(var issues: List<Issue>, private val identityManager: IdentityManager) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var layoutInflater: LayoutInflater

    var onIssueShow: ((IssueListViewModel) -> Unit)? = null
    var onIssueEdit: ((IssueListViewModel) -> Unit)? = null
    var onIssueDelete: ((IssueListViewModel) -> Unit)? = null

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

        val entity = issues[position]
        val viewModel = IssueListViewModel(entity)

        if (identityManager.identity?.username == entity.owner) {
            viewModel.editVisibility.value = View.VISIBLE
        } else {
            viewModel.editVisibility.value = View.GONE
        }

        viewModel.onIssueShow = {
            onIssueShow?.invoke(viewModel)
        }

        viewModel.onIssueEdit = {
            onIssueEdit?.invoke(viewModel)
        }

        holder.binding.viewModel = viewModel
    }
}