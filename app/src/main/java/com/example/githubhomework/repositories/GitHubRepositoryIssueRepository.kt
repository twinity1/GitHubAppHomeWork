package com.example.githubhomework.repositories

import com.example.githubhomework.entities.GitHubRepositoryIssue
import com.example.githubhomework.tools.ApiGetMultipleRequest
import com.example.githubhomework.tools.ApiGetSingleRequest

class GitHubRepositoryIssueRepository {
    companion object {
        val shared = GitHubRepositoryIssueRepository()
    }


    private val baseApiUrl = "https://api.github.com/repos/"

    fun findAll(repositoryFullName: String, completionHandler: (Result<List<GitHubRepositoryIssue>>) -> Unit) {
        val request = ApiGetMultipleRequest()

        request.getAsList(baseApiUrl + repositoryFullName + "/issues", GitHubRepositoryIssue::class.java) {
            completionHandler(it)
        }
    }

    fun findSingle(issueUrl: String, completionHandler: (Result<GitHubRepositoryIssue>) -> Unit) {
        ApiGetSingleRequest().getAsObject(issueUrl, GitHubRepositoryIssue::class.java, completionHandler)
    }
}