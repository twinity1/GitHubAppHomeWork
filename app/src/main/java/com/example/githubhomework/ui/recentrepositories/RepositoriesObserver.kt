package com.example.githubhomework.ui.recentrepositories

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import com.example.githubhomework.RepositoryActivity
import com.example.githubhomework.persistence.entities.Repository

class RepositoriesObserver {
    fun create(owner: RecentRepositoriesFragment): Observer<List<Repository>> {
        return Observer {
            setAdapter(owner)

            if (it.count() == 0) {
                owner.viewModel.itemsVisibility.value = View.GONE
                owner.viewModel.noRepositoryVisibility.value = View.VISIBLE
            } else {
                owner.viewModel.itemsVisibility.value = View.VISIBLE
                owner.viewModel.noRepositoryVisibility.value = View.GONE
            }
        }
    }

    private fun setAdapter(owner: RecentRepositoriesFragment) {
        if (owner.isGridViewInitialized() && owner.viewModel.repositories.value != null) {
            val gridViewAdapter = GridViewAdapter(owner.requireContext(), owner.viewModel.repositories.value!!)
            owner.gridView.adapter = gridViewAdapter

            gridViewAdapter.onRepositorySelect = {
                val intent = Intent(owner.requireContext(), RepositoryActivity::class.java)

                intent.putExtra(RepositoryActivity.REPOSITORY_FULLNAME, it.fullName)

                owner.startActivity(intent)
            }
        }
    }
}