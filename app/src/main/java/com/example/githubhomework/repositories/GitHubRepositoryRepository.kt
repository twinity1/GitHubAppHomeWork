package com.example.githubhomework.repositories

import android.os.Handler
import android.os.Looper
import com.example.githubhomework.entities.GitHubRepository
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException
import java.lang.IllegalStateException
import java.net.URL

class GitHubRepositoryRepository {
    private val httpClient = OkHttpClient()

    companion object {
        val shared = GitHubRepositoryRepository()
    }

    fun findAllByReposUrl(reposUrl: String, completionHandler: (Result<List<GitHubRepository>>) -> Unit) {
        val url = URL(reposUrl)

        val request = Request.Builder().url(url).build()

        val uiHandler = Handler(Looper.getMainLooper())

        httpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                uiHandler.post {
                    completionHandler(Result.failure(e))
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body()!!.string()

                try {
                    val list = parseJsonResult(body!!)

                    uiHandler.post {
                        completionHandler(Result.success(list))
                    }

                } catch (e: IllegalStateException) {
                    completionHandler(Result.failure(e))
                } catch (e: JsonSyntaxException) {
                    completionHandler(Result.failure(e))
                }

            }
        })
    }

    private fun parseJsonResult(content: String): List<GitHubRepository> {
        val value = object : TypeToken<List<GitHubRepository>>() {}

        val gson = GsonBuilder().create()

        val result = gson.fromJson<List<GitHubRepository>>(content, value.type)

        return result
    }

}