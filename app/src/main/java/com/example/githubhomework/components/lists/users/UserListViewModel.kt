package com.example.githubhomework.components.lists.users

import com.example.githubhomework.entities.User

class UserListViewModel(val gitHubUser: User) {
    var onShowUser = {}

    fun onCardClick() {
        onShowUser()
    }
}