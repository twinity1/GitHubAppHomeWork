package com.example.githubhomework.ui.recentrepositories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubhomework.persistence.entities.Repository

class RecentRepositoryViewModel : ViewModel() {
    val repository = MutableLiveData<Repository>()

    var onRepositorySelect = {}

    fun repositorySelect() {
        onRepositorySelect()
    }
}