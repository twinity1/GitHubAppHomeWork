package com.example.githubhomework.persistence.entities

import com.google.gson.annotations.SerializedName

data class Issue(
    var title: String,

    var body: String,

    @SerializedName("labels_url")
    var labelsUrl: String = "",

    var number: Int = 0,

    var url: String = "",

    var labels: List<Label> = listOf()
)
{
    val numberWithHash
        get() = "#" + number.toString()
}