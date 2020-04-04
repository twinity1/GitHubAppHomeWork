package com.example.githubhomework.persistence.daos

import androidx.room.*
import com.example.githubhomework.persistence.entities.Issue
import com.example.githubhomework.persistence.entities.LabelDataConverter
import com.example.githubhomework.persistence.entities.Repository
import com.example.githubhomework.persistence.entities.User

@Dao
interface RepositoryDao {
    @Insert
    fun insert(repository: Repository)

    @Update
    fun update(repository: Repository)

    @Query("SELECT * FROM Repository WHERE fullName = :fullName LIMIT 1")
    fun findOneByFullName(fullName: String): Repository?

    @Query("SELECT * FROM REPOSITORY WHERE ownerReposUrl = :reposUrl")
    fun findAllByReposUrl(reposUrl: String): List<Repository>
}