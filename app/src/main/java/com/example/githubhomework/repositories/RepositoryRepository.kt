package com.example.githubhomework.repositories

import com.example.githubhomework.entities.Repository
import com.example.githubhomework.tools.ApiGetMultipleRequest

class RepositoryRepository {
    companion object {
        val shared = RepositoryRepository()
    }

    fun findAllByReposUrl(reposUrl: String, completionHandler: (Result<List<Repository>>) -> Unit) {
        ApiGetMultipleRequest().getAsList(reposUrl, Repository::class.java) {
            completionHandler(it)
        }
    }
}