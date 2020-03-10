package com.example.githubhomework.ui.home

import com.example.githubhomework.entities.GitHubUser

class HomeListViewModel(val gitHubUser: GitHubUser) {
    var onShowUser = {}

    fun onCardClick() {
        onShowUser()
    }
}