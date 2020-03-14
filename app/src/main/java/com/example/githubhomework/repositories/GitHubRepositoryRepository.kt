package com.example.githubhomework.repositories

import com.example.githubhomework.entities.GitHubRepository
import com.example.githubhomework.tools.ApiGetMultipleRequest

class GitHubRepositoryRepository {
    companion object {
        val shared = GitHubRepositoryRepository()
    }

    fun findAllByReposUrl(reposUrl: String, completionHandler: (Result<List<GitHubRepository>>) -> Unit) {
        ApiGetMultipleRequest().getAsList(reposUrl, GitHubRepository::class.java) {
            completionHandler(it)
        }
    }
}