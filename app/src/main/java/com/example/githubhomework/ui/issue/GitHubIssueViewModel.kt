package com.example.githubhomework.ui.issue

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubhomework.entities.GitHubRepositoryIssue

class GitHubIssueViewModel : ViewModel() {
    var entity = MutableLiveData<GitHubRepositoryIssue>()
}