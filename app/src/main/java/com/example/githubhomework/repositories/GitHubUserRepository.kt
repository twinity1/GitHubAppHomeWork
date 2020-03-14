package com.example.githubhomework.repositories

import com.example.githubhomework.entities.GitHubUser
import com.example.githubhomework.tools.ApiGetRequest
import okhttp3.*
import java.net.URLEncoder

class GitHubUserRepository {
    private val baseUserApiUrl = "https://api.github.com/search/users?q="

    private val httpClient = OkHttpClient()

    companion object {
        val shared = GitHubUserRepository()
    }

    fun findByName(name: String, completionHandler: (Result<List<GitHubUser>>) -> Unit) {
        val userFindUrl = baseUserApiUrl + URLEncoder.encode( name, "UTF-8")

        val apiRequest = ApiGetRequest()

        apiRequest.onJsonParsing = { it.asJsonObject["items"].asJsonArray }

        apiRequest.getAsList(userFindUrl, GitHubUser::class.java) {
            completionHandler(it)
        }
    }
}