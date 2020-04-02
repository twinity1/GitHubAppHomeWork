package com.example.githubhomework.ui.searchuser

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubhomework.UserActivity
import com.example.githubhomework.components.lists.users.UserListAdapter
import com.example.githubhomework.persistence.entities.User


class RecycleViewObserver {
    fun create(owner: SearchUserFragment): Observer<List<User>> {
        return Observer {
            owner.binding.searchRecycleView.apply {
                val userAdapter = UserListAdapter(it)
                userAdapter.onShowUser = {
                    val intent = Intent(owner.activity, UserActivity::class.java)

                    intent.putExtra(UserActivity.EXTRA_GITHUB_REPOSITORY_URL, it.gitHubUser.reposUrl)

                    owner.startActivity(intent)
                }

                layoutManager = LinearLayoutManager(owner.activity)
                adapter = userAdapter
            }
        }
    }
}