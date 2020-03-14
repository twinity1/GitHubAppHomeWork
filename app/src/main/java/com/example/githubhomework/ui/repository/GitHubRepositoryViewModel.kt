package com.example.githubhomework.ui.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubhomework.entities.GitHubRepositoryIssue

class GitHubRepositoryViewModel : ViewModel() {
    var issueList = MutableLiveData<List<GitHubRepositoryIssue>>()
}