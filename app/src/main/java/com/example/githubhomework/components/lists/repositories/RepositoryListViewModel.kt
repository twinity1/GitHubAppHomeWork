package com.example.githubhomework.components.lists.repositories

import androidx.lifecycle.ViewModel
import com.example.githubhomework.entities.Repository

class RepositoryListViewModel(val entity: Repository) : ViewModel() {
    var onRepositoryShow = {}

    fun onCardSelected() {
        onRepositoryShow()
    }
}