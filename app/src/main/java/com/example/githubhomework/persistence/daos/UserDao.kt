package com.example.githubhomework.persistence.daos

import androidx.room.*
import com.example.githubhomework.persistence.entities.Issue
import com.example.githubhomework.persistence.entities.LabelDataConverter
import com.example.githubhomework.persistence.entities.Repository
import com.example.githubhomework.persistence.entities.User

@Dao
interface UserDao {
    @Query("SELECT DISTINCT ownerLogin as login, ownerReposUrl as reposUrl, null as uid  FROM Repository WHERE ownerLogin LIKE '%' || :ownerName || '%'")
    fun searchByNameInRepositories(ownerName: String): List<User>
}