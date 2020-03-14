package com.example.githubhomework.components.lists.issues

import androidx.lifecycle.ViewModel
import com.example.githubhomework.entities.GitHubRepository
import com.example.githubhomework.entities.GitHubRepositoryIssue

class RepositoryIssueListViewModel(val entity: GitHubRepositoryIssue) : ViewModel() {
    var number: String = ""
        get() = "#" + entity.number.toString()

}