package com.example.githubhomework.ui.githubuser

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubhomework.entities.GitHubRepository

class GitHubUserViewModel : ViewModel() {
    var repositoryList = MutableLiveData<List<GitHubRepository>>()
}