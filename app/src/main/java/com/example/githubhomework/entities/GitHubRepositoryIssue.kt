package com.example.githubhomework.entities

import com.google.gson.annotations.SerializedName

data class GitHubRepositoryIssue(
    val title: String,

    @SerializedName("labels_url")
    val labelsUrl: String,

    val number: Int
)