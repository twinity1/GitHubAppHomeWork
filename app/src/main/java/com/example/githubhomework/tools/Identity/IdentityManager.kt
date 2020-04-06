package com.example.githubhomework.tools.Identity

import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import com.example.githubhomework.persistence.AppDatabase
import com.example.githubhomework.persistence.entities.Identity
import com.example.githubhomework.persistence.repositories.RepositoryRepository
import com.example.githubhomework.tools.HttpClient.BasicCredentialsInterceptor
import com.example.githubhomework.tools.HttpClient.HttpClient
import com.google.gson.Gson
import com.google.gson.JsonParser
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.io.IOException

class IdentityManager(
    private val httpClient: HttpClient,
    private val basicCredentialsInterceptor: BasicCredentialsInterceptor,
    private val appDatabase: AppDatabase
) : KoinComponent
{
    private val repositoryRepository: RepositoryRepository by inject()

    var identity: Identity? = null

    init {
        AsyncTask.execute {
            identity = appDatabase.identityDao().getIdentity()
            if (identity != null) {
                basicCredentialsInterceptor.username = identity!!.username
                basicCredentialsInterceptor.password = identity!!.password
            }
        }
    }

    class BadCredentials(message: String) : Exception(message)
    fun signIn(username: String, password: String, completionHandler: (Result<Identity>) -> Unit) {
        basicCredentialsInterceptor.username = username
        basicCredentialsInterceptor.password = password

        val handler = Handler(Looper.getMainLooper())

        val r = Request.Builder().url("https://api.github.com/user").build();

        httpClient.client.newCall(r).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                handler.post {
                    identity = null
                    basicCredentialsInterceptor.username = null
                    basicCredentialsInterceptor.password = null
                    completionHandler(Result.failure(e))
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.code() == 200) {
                    val username1 = JsonParser().parse(response.body()!!.string()).asJsonObject.get("login").asString
                    val newIdentity = appDatabase.identityDao().getIdentity() ?: Identity(null,
                        username1, password)
                    appDatabase.identityDao().save(newIdentity)
                    identity = newIdentity
                    repositoryRepository.storeAllRepositoriesForCurrentUser()

                    handler.post {
                        completionHandler(Result.success(newIdentity))
                    }
                } else {
                    val responseString = response.body()!!.string()

                    if (response.code() == 401) {
                        val message = JsonParser().parse(responseString).asJsonObject.get("message").asString

                        handler.post {
                            completionHandler(Result.failure(BadCredentials(message)))
                        }
                    }
                }
            }
        })
    }

    fun signOut(completionHandler: () -> Unit) {
        AsyncTask.execute {
            basicCredentialsInterceptor.username = null
            basicCredentialsInterceptor.password = null
            appDatabase.identityDao().delete();
            identity = null

            completionHandler()
        }
    }
}