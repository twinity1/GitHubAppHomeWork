package com.example.githubhomework.persistence.repositories

import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import com.example.githubhomework.persistence.AppDatabase
import com.example.githubhomework.persistence.entities.User
import com.example.githubhomework.tools.api.ApiGetMultipleRequest
import okhttp3.*
import java.net.URLEncoder
import java.net.UnknownHostException

class UserRepository(private val multipleRequest: ApiGetMultipleRequest, private val appDatabase: AppDatabase) {

    fun findByName(name: String, completionHandler: (Result<List<User>>) -> Unit) {
        val baseUserApiUrl = "https://api.github.com/search/users?q="

        val userFindUrl = baseUserApiUrl + URLEncoder.encode( name, "UTF-8")

        multipleRequest.onJsonParse = { it.asJsonObject["items"].asJsonArray }

        multipleRequest.getAsList(userFindUrl, User::class.java, {
            val result = it

            it.fold(
                onSuccess = {
                    completionHandler(result)
                },
                onFailure = {
                    if (it is UnknownHostException) {
                         findByNameLocalStorage(name) { completionHandler(Result.success(it)) }
                    } else {
                        completionHandler(result)
                    }
                }
            )
        })
    }

    private fun findByNameLocalStorage(name: String, completionHandler: (List<User>) -> Unit) {
        AsyncTask.execute {
            val result = appDatabase.userDao().searchByNameInRepositories(name)

            Handler(Looper.getMainLooper()).post {
                completionHandler(result)
            }
        }
    }
}