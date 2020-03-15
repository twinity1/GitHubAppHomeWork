package com.example.githubhomework.entities

import com.google.gson.annotations.SerializedName

data class Issue(
    val title: String,

    @SerializedName("labels_url")
    val labelsUrl: String,

    val number: Int,

    val url: String,

    val body: String
)