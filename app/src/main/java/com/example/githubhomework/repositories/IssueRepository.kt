package com.example.githubhomework.repositories

import com.example.githubhomework.entities.Issue
import com.example.githubhomework.tools.ApiGetMultipleRequest
import com.example.githubhomework.tools.ApiGetSingleRequest

class IssueRepository {
    companion object {
        val shared = IssueRepository()
    }

    private val baseApiUrl = "https://api.github.com/repos/"

    fun findAll(repositoryFullName: String, completionHandler: (Result<List<Issue>>) -> Unit) {
        val request = ApiGetMultipleRequest()

        request.getAsList(baseApiUrl + repositoryFullName + "/issues", Issue::class.java) {
            completionHandler(it)
        }
    }

    fun findSingle(issueUrl: String, completionHandler: (Result<Issue>) -> Unit) {
        ApiGetSingleRequest().getAsObject(issueUrl, Issue::class.java, completionHandler)
    }
}