package com.example.githubhomework.repositories

import com.example.githubhomework.entities.GitHubRepositoryIssue
import com.example.githubhomework.tools.ApiGetRequest

class GitHubRepositoryIssueRepository {
    companion object {
        val shared = GitHubRepositoryIssueRepository()
    }


    private val baseApiUrl = "https://api.github.com/repos/"

    fun findAll(repositoryFullName: String, completionHandler: (Result<List<GitHubRepositoryIssue>>) -> Unit) {
        val request = ApiGetRequest()

        request.getAsList(baseApiUrl + repositoryFullName + "/issues", GitHubRepositoryIssue::class.java) {
            completionHandler(it)
        }
    }
}