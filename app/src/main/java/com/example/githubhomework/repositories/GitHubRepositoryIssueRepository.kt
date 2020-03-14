package com.example.githubhomework.repositories

import com.example.githubhomework.entities.GitHubRepositoryIssue
import com.example.githubhomework.tools.ApiGetRequest
import okhttp3.*

class GitHubRepositoryIssueRepository {
    private val httpClient = OkHttpClient()

    companion object {
        val shared = GitHubRepositoryIssueRepository()
    }

    fun findAll(issuesUrl: String, completionHandler: (Result<List<GitHubRepositoryIssue>>) -> Unit) {
        val request = ApiGetRequest()

        request.send<List<GitHubRepositoryIssue>>(issuesUrl) {
            completionHandler(it)
        }
    }
}