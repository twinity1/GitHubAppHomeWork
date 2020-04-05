package com.example.githubhomework.ui.user

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubhomework.RepositoryActivity
import com.example.githubhomework.components.lists.repositories.RepositoryListAdapter
import com.example.githubhomework.persistence.entities.Repository
import com.example.githubhomework.persistence.entities.User
import kotlinx.android.synthetic.main.fragment_user.*

class UserObserver {
    fun create(owner: UserFragment): Observer<List<Repository>> {
        return Observer {
            setVisibility(it, owner)

            setAdapter(it, owner)
        }
    }

    private fun setVisibility(
        it: List<Repository>,
        owner: UserFragment
    ) {
        if (it.count() == 0) {
            owner.viewModel.itemsVisibility.value = View.GONE
            owner.viewModel.noFoundTextVisibility.value = View.VISIBLE
        } else {
            owner.viewModel.itemsVisibility.value = View.VISIBLE
            owner.viewModel.noFoundTextVisibility.value = View.GONE
        }
    }

    private fun setAdapter(
        it: List<Repository>,
        owner: UserFragment
    ) {
        val repositoryListAdapter = RepositoryListAdapter(it)

        owner.userList.layoutManager = LinearLayoutManager(owner.requireContext())
        owner.userList.adapter = repositoryListAdapter

        repositoryListAdapter.onShowRepository = {
            val intent = Intent(owner.requireContext(), RepositoryActivity::class.java)

            intent.putExtra(RepositoryActivity.REPOSITORY_FULLNAME, it.entity.fullName)

            owner.startActivity(intent)
        }
    }
}