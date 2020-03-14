package com.example.githubhomework.components.lists.repositories

import androidx.lifecycle.ViewModel
import com.example.githubhomework.entities.GitHubRepository

class RepositoryListViewModel(val entity: GitHubRepository) : ViewModel() {
    var onRepositoryShow = {}

    fun onCardSelected() {
        onRepositoryShow()
    }
}