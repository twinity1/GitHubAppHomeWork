package com.example.githubhomework.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubhomework.persistence.daos.IdentityDao
import com.example.githubhomework.persistence.entities.Identity

@Database(entities = arrayOf(Identity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun identityDao(): IdentityDao
}