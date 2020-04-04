package com.example.githubhomework.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class User(
    @ColumnInfo(name = "login")
    var login: String,

    @ColumnInfo(name = "url")
    var url: String,

    @SerializedName("avatar_url")
    var avatarUrl: String,

    @ColumnInfo(name = "reposUrl")
    @SerializedName("repos_url")
    var reposUrl: String,

    @PrimaryKey
    var uid: Int? = null
)
