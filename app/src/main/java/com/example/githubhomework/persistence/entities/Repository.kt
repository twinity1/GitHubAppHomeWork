package com.example.githubhomework.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class Repository(
    @PrimaryKey
    var uid: Int? = null,

    @ColumnInfo
    val name: String,

    @ColumnInfo
    var ownerLogin: String,

    @ColumnInfo
    var ownerReposUrl: String,

    @SerializedName("full_name")
    @ColumnInfo
    val fullName: String,

    @SerializedName("issues_url")
    @ColumnInfo
    val issuesUrl: String,

    @SerializedName("labels_url")
    @ColumnInfo
    val labelsUrl: String,

    @ColumnInfo
    var lastVisitTimestamp: Int? = null
)