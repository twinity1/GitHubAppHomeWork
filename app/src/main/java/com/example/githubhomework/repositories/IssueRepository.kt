package com.example.githubhomework.repositories

import android.util.Log
import com.example.githubhomework.entities.Issue
import com.example.githubhomework.tools.ApiGetMultipleRequest
import com.example.githubhomework.tools.ApiGetSingleRequest
import com.example.githubhomework.tools.HttpClient.HttpClient
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class IssueRepository(private val multipleRequest: ApiGetMultipleRequest, private val singleRequest: ApiGetSingleRequest, private val httpClient: HttpClient) {
    private val baseApiUrl = "https://api.github.com/repos/"

    fun findAll(repositoryFullName: String, completionHandler: (Result<List<Issue>>) -> Unit) {
        multipleRequest.getAsList(baseApiUrl + repositoryFullName + "/issues", Issue::class.java) {
            completionHandler(it)
        }
    }

    fun findSingle(issueUrl: String, completionHandler: (Result<Issue>) -> Unit) {
        singleRequest.getAsObject(issueUrl, Issue::class.java, completionHandler)
    }

    private data class IssueCreateDto(val title: String, val body: String)
    fun saveIssue(repositoryFullName: String, issue: Issue, completionHandler: (Result<Issue>) -> Unit) {
        val jsonBody = Gson().toJson(IssueCreateDto(title = issue.title, body = issue.body))

        val body = RequestBody.create(MediaType.parse("application/json"), jsonBody);

        val r = Request.Builder()
            .url("https://api.github.com/repos/${repositoryFullName}/issues")
            .post(body)
            .build()

        httpClient.client.newCall(r).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                completionHandler(Result.failure(e))
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.code() == 201) {
                    Log.e("x", "x")
                }
            }
        })
    }
}