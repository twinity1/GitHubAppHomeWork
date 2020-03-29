package com.example.githubhomework.entities

data class Identity (var username: String, var password: String) {
    fun isOwnerOf(repositoryFullName: String): Boolean
    {
        val repositoryUsername = repositoryFullName.split("/")[0]

        return repositoryUsername.toLowerCase() == username.toLowerCase()
    }
}