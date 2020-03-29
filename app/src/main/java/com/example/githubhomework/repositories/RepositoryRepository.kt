package com.example.githubhomework.repositories

import com.example.githubhomework.entities.Repository
import com.example.githubhomework.tools.ApiGetMultipleRequest

class RepositoryRepository(private val multipleRequest: ApiGetMultipleRequest) {
    fun findAllByReposUrl(reposUrl: String, completionHandler: (Result<List<Repository>>) -> Unit) {
        multipleRequest.getAsList(reposUrl, Repository::class.java) {
            completionHandler(it)
        }
    }
}