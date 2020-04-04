package com.example.githubhomework.persistence.daos

import androidx.room.*
import com.example.githubhomework.persistence.entities.Issue
import com.example.githubhomework.persistence.entities.LabelDataConverter
import com.example.githubhomework.persistence.entities.Repository
import com.example.githubhomework.persistence.entities.User

@Dao
interface UserDao {
    @Query("SELECT * FROM Repository WHERE own = :fullName LIMIT 1")
    fun searchByNameInRepositories(ownerName: String): List<User>
}