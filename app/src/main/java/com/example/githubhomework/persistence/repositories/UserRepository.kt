package com.example.githubhomework.persistence.repositories

import android.os.AsyncTask
import com.example.githubhomework.persistence.AppDatabase
import com.example.githubhomework.persistence.entities.User
import com.example.githubhomework.tools.api.ApiGetMultipleRequest
import okhttp3.*
import java.net.URLEncoder

class UserRepository(private val multipleRequest: ApiGetMultipleRequest, private val appDatabase: AppDatabase) {
    private val baseUserApiUrl = "https://api.github.com/search/users?q="

    fun findByName(name: String, completionHandler: (Result<List<User>>) -> Unit) {
        val userFindUrl = baseUserApiUrl + URLEncoder.encode( name, "UTF-8")

        multipleRequest.onJsonParse = { it.asJsonObject["items"].asJsonArray }

        multipleRequest.getAsList(userFindUrl, User::class.java, {
            completionHandler(it)
        })
    }
}