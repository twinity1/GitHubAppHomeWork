package com.example.githubhomework.components.lists.users

import com.example.githubhomework.entities.GitHubUser

class HomeListViewModel(val gitHubUser: GitHubUser) {
    var onShowUser = {}

    fun onCardClick() {
        onShowUser()
    }
}