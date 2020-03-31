package com.example.githubhomework.persistence.entities

import com.google.gson.annotations.SerializedName

data class User(
    var login: String,

    var url: String,

    @SerializedName("avatar_url")
    var avatarUrl: String,

    @SerializedName("repos_url")
    var reposUrl: String
)
