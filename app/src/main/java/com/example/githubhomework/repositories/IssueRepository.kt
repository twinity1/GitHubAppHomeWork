package com.example.githubhomework.repositories

import com.example.githubhomework.entities.Issue
import com.example.githubhomework.tools.ApiGetMultipleRequest
import com.example.githubhomework.tools.ApiGetSingleRequest

class IssueRepository(private val multipleRequest: ApiGetMultipleRequest, private val singleRequest: ApiGetSingleRequest) {
    private val baseApiUrl = "https://api.github.com/repos/"

    fun findAll(repositoryFullName: String, completionHandler: (Result<List<Issue>>) -> Unit) {
        multipleRequest.getAsList(baseApiUrl + repositoryFullName + "/issues", Issue::class.java) {
            completionHandler(it)
        }
    }

    fun findSingle(issueUrl: String, completionHandler: (Result<Issue>) -> Unit) {
        singleRequest.getAsObject(issueUrl, Issue::class.java, completionHandler)
    }
}