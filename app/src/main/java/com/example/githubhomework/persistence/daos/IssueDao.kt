package com.example.githubhomework.persistence.daos

import androidx.room.*
import com.example.githubhomework.persistence.entities.Issue
import com.example.githubhomework.persistence.entities.LabelDataConverter
import com.example.githubhomework.persistence.entities.User

@Dao
interface IssueDao {
    @Insert
    fun insert(user: Issue)

    @Update
    fun update(user: Issue)

    @Query("SELECT * FROM Issue WHERE url = :url LIMIT 1")
    fun findOneByUrl(url: String): Issue?

    @Query("SELECT * FROM Issue WHERE repositoryName = :repositoryName")
    fun findAllByRepositoryName(repositoryName: String): List<Issue>
}