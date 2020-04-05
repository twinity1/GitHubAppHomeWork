package com.example.githubhomework.ui.repository

import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubhomework.IssueActivity
import com.example.githubhomework.IssueFormActivity
import com.example.githubhomework.R
import com.example.githubhomework.components.lists.issues.IssuesListAdapter
import com.example.githubhomework.persistence.entities.Issue
import kotlinx.android.synthetic.main.fragment_repository.*

class IssueObserver {
    fun create(owner: RepositoryFragment): Observer<List<Issue>>
    {
        return Observer {
            val adapter = IssuesListAdapter(it)

            setOnIssueShowEvenet(adapter, owner)
            setOnIssueDeleteEvent(adapter, owner)
            setOnIssueEditEvent(adapter, owner)

            owner.repositoryIssueList.layoutManager = LinearLayoutManager(owner.requireActivity())
            owner.repositoryIssueList.adapter = adapter
        }
    }

    private fun setOnIssueEditEvent(
        adapter: IssuesListAdapter,
        owner: RepositoryFragment
    ) {
        adapter.onIssueEdit = {
            val intent = Intent(owner.requireActivity(), IssueFormActivity::class.java)

            intent.putExtra(IssueFormActivity.FULL_REPOSITORY_NAME, owner.repositoryFullName)
            intent.putExtra(IssueFormActivity.ISSUE_URL, it.entity.url)

            owner.startActivity(intent)
        }
    }

    private fun setOnIssueDeleteEvent(
        adapter: IssuesListAdapter,
        owner: RepositoryFragment
    ) {
        adapter.onIssueDelete = {
            val dialog = IssueDeleteConfirmDialogFactory().create(owner.requireContext()) {
                if (it) {

                }
            }

            dialog.show()
        }
    }

    private fun setOnIssueShowEvenet(
        adapter: IssuesListAdapter,
        owner: RepositoryFragment
    ) {
        adapter.onIssueShow = {
            val intent = Intent(owner.requireActivity(), IssueActivity::class.java)

            intent.putExtra(IssueActivity.ISSUE_URL, it.entity.url)

            owner.startActivity(intent)
        }
    }
}