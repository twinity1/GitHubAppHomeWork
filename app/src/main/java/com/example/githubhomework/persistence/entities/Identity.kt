package com.example.githubhomework.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Identity (
    @PrimaryKey val uid: Int?,

    @ColumnInfo(name = "username")
    var username: String,

    @ColumnInfo(name = "password")
    var password: String
) {
    fun isOwnerOf(repositoryFullName: String): Boolean
    {
        val repositoryUsername = repositoryFullName.split("/")[0]

        return repositoryUsername.toLowerCase() == username.toLowerCase()
    }
}