package com.example.githubhomework.persistence.entities

data class Label(
    val id: Int,

    val url: String,

    val name: String,

    val color: String,

    val description: String
)