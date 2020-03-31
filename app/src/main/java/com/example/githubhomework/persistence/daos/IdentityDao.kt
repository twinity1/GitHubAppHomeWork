package com.example.githubhomework.persistence.daos

import androidx.room.*
import com.example.githubhomework.persistence.entities.Identity

@Dao
interface IdentityDao {
    @Query("SELECT * FROM identity LIMIT 1")
    fun getIdentity(): Identity?

    @Insert
    fun insert(identity: Identity)

    @Update
    fun update(identity: Identity)

    @Transaction
    fun save(identity: Identity) {
        if (identity.uid == null) {
            insert(identity)
        } else {
            update(identity)
        }
    }
}