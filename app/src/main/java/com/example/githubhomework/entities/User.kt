package com.example.githubhomework.entities

import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.PropertyKey

data class User(
    var login: String,

    var url: String,

    @SerializedName("avatar_url")
    var avatarUrl: String,

    @SerializedName("repos_url")
    var reposUrl: String
)