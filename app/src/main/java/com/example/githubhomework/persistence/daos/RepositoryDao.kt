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

    @Query("SELECT * FROM Repository WHERE ownerReposUrl = :reposUrl")
    fun findAllByReposUrl(reposUrl: String): List<Repository>

    @Query("SELECT * FROM Repository WHERE ownerLogin != :owner OR :owner IS NULL ORDER BY lastVisitTimestamp DESC LIMIT :limit")
    fun findAllUnownedRecentVisited(limit: Int = 8, owner: String?): List<Repository>

    @Query("SELECT * FROM Repository WHERE ownerLogin = :owner ORDER BY lastVisitTimestamp DESC LIMIT :limit")
    fun findAllOwnedRecentVisited(limit: Int = 8, owner: String): List<Repository>
}