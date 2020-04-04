package com.example.githubhomework.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.githubhomework.persistence.daos.IdentityDao
import com.example.githubhomework.persistence.daos.IssueDao
import com.example.githubhomework.persistence.daos.RepositoryDao
import com.example.githubhomework.persistence.entities.*

@Database(entities = arrayOf(Identity::class, Issue::class, Repository::class), version = 4)
@TypeConverters(LabelDataConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun identityDao(): IdentityDao

    abstract fun issueDao(): IssueDao

    abstract fun repositoryDao(): RepositoryDao
}