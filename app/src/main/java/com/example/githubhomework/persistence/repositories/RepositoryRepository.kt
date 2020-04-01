package com.example.githubhomework.persistence.repositories

import com.example.githubhomework.persistence.entities.Repository
import com.example.githubhomework.tools.api.ApiGetMultipleRequest

class RepositoryRepository(private val multipleRequest: ApiGetMultipleRequest) {
    fun findAllByReposUrl(reposUrl: String, completionHandler: (Result<List<Repository>>) -> Unit) {
        multipleRequest.getAsList(reposUrl, Repository::class.java) {
            completionHandler(it)
        }
    }
}