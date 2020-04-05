package com.example.githubhomework.ui.searchuser

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubhomework.UserActivity
import com.example.githubhomework.components.lists.users.UserListAdapter
import com.example.githubhomework.persistence.entities.User


class RecycleViewObserver {
    fun create(owner: SearchUserFragment): Observer<List<User>> {
        return Observer {
            setVisibility(it, owner)
            setRecycleView(owner, it)
        }
    }

    private fun setRecycleView(
        owner: SearchUserFragment,
        it: List<User>
    ) {
        owner.binding.searchRecycleView.apply {
            val userAdapter = UserListAdapter(it)
            userAdapter.onShowUser = {

                val imm = owner.requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
                imm?.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)

                val intent = Intent(owner.activity, UserActivity::class.java)

                intent.putExtra(UserActivity.EXTRA_GITHUB_REPOSITORY_URL, it.gitHubUser.reposUrl)

                owner.startActivity(intent)
            }

            layoutManager = LinearLayoutManager(owner.activity)
            adapter = userAdapter
        }
    }

    private fun setVisibility(
        it: List<User>,
        owner: SearchUserFragment
    ) {
        if (owner.viewModel.searchText.value == "") {
            owner.viewModel.itemsVisibility.value = View.GONE
            owner.viewModel.noFoundTextVisibility.value = View.GONE
            return
        }

        if (it.count() == 0) {
            owner.viewModel.itemsVisibility.value = View.GONE
            owner.viewModel.noFoundTextVisibility.value = View.VISIBLE
        } else {
            owner.viewModel.itemsVisibility.value = View.VISIBLE
            owner.viewModel.noFoundTextVisibility.value = View.GONE
        }
    }
}