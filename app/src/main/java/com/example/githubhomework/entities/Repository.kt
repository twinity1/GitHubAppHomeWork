package com.example.githubhomework.entities

import com.google.gson.annotations.SerializedName

data class Repository(
    val id: Int,

    val name: String,

    @SerializedName("full_name")
    val fullName: String
)