package com.example.githubhomework.components.lists.users

import com.example.githubhomework.entities.GitHubUser

class GitHubUserListViewModel(val gitHubUser: GitHubUser) {
    var onShowUser = {}

    fun onCardClick() {
        onShowUser()
    }
}