package com.example.githubhomework.repositories

import com.example.githubhomework.entities.User
import com.example.githubhomework.tools.ApiGetMultipleRequest
import okhttp3.*
import java.net.URLEncoder

class UserRepository {
    private val baseUserApiUrl = "https://api.github.com/search/users?q="

    private val httpClient = OkHttpClient()

    companion object {
        val shared = UserRepository()
    }

    fun findByName(name: String, completionHandler: (Result<List<User>>) -> Unit) {
        val userFindUrl = baseUserApiUrl + URLEncoder.encode( name, "UTF-8")

        val apiRequest = ApiGetMultipleRequest()

        apiRequest.onJsonParse = { it.asJsonObject["items"].asJsonArray }


        apiRequest.getAsList(userFindUrl, User::class.java) {
            completionHandler(it)
        }
    }
}