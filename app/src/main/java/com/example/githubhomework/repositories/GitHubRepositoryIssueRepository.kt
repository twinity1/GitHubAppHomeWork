package com.example.githubhomework.repositories

import com.example.githubhomework.entities.GitHubRepositoryIssue
import com.example.githubhomework.tools.ApiGetRequest
import okhttp3.*

class GitHubRepositoryIssueRepository {
    companion object {
        val shared = GitHubRepositoryIssueRepository()
    }


    private val baseApiUrl = "https://api.github.com/repos/"

    fun findAll(repositoryFullName: String, completionHandler: (Result<List<GitHubRepositoryIssue>>) -> Unit) {
        val request = ApiGetRequest()

        request.send<List<GitHubRepositoryIssue>>(baseApiUrl + repositoryFullName + "/issues") {
            completionHandler(it)
        }
    }
}