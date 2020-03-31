package com.example.githubhomework.components.lists.repositories

import androidx.lifecycle.ViewModel
import com.example.githubhomework.persistence.entities.Repository

class RepositoryListViewModel(val entity: Repository) : ViewModel() {
    var onRepositoryShow = {}

    fun onCardSelected() {
        onRepositoryShow()
    }
}