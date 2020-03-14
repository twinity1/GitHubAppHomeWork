package com.example.githubhomework.repositories

import com.example.githubhomework.entities.GitHubRepository
import com.example.githubhomework.tools.ApiGetRequest

class GitHubRepositoryRepository {
    companion object {
        val shared = GitHubRepositoryRepository()
    }

    fun findAllByReposUrl(reposUrl: String, completionHandler: (Result<List<GitHubRepository>>) -> Unit) {
        ApiGetRequest().getAsList(reposUrl, GitHubRepository::class.java) {
            completionHandler(it)
        }
    }
}