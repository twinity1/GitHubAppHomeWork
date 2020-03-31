package com.example.githubhomework.persistence.entities

import com.google.gson.annotations.SerializedName

data class Repository(
    val id: Int,

    val name: String,

    @SerializedName("full_name")
    val fullName: String,

    @SerializedName("issues_url")
    val issuesUrl: String,

    @SerializedName("labels_url")
    val labelsUrl: String
)