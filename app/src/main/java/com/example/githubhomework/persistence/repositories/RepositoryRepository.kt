package com.example.githubhomework.persistence.repositories

import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.room.Query
import com.example.githubhomework.persistence.AppDatabase
import com.example.githubhomework.persistence.entities.Repository
import com.example.githubhomework.persistence.entities.User
import com.example.githubhomework.tools.Identity.IdentityManager
import com.example.githubhomework.tools.api.ApiGetMultipleRequest
import com.example.githubhomework.tools.api.ApiGetSingleRequest
import com.google.gson.JsonElement
import java.lang.Exception
import java.net.UnknownHostException
import java.time.LocalDateTime
import java.util.*

class RepositoryRepository(
    private val multipleRequest: ApiGetMultipleRequest,
    private val singleRequest: ApiGetSingleRequest,
    private val appDatabase: AppDatabase,
    private val identityManager: IdentityManager,
    private val issueRepository: IssueRepository
) {

    private val repositoryHydrator: ((JsonElement, Repository) -> Unit) = { jsonElement, repository ->
        repository.ownerLogin = jsonElement.asJsonObject.get("owner").asJsonObject.get("login").asString
        repository.ownerReposUrl = jsonElement.asJsonObject.get("owner").asJsonObject.get("repos_url").asString
    }

    fun findAllByReposUrl(reposUrl: String, completionHandler: (Result<List<Repository>>) -> Unit) {
        multipleRequest.getAsList(reposUrl, Repository::class.java, {
            val result= it

            it.fold(
                onSuccess = {
                    completionHandler(result)
                },
                onFailure = {
                    if (it is UnknownHostException) {
                        findAllByReposUrlLocalStorage(reposUrl) { completionHandler(Result.success(it)) }
                    } else {
                        completionHandler(result)
                    }
                }
            )
        }, repositoryHydrator)
    }

    class RepositoryNotFound : Exception()
    fun findSingle(repositoryName: String, completionHandler: (Result<Repository>) -> Unit) {
        singleRequest.getAsObject("https://api.github.com/repos/${repositoryName}", Repository::class.java, {
            val result = it

            it.fold(
                onSuccess = {
                    storeRepositoryToLocalStorage(it)

                    completionHandler(result)
                },
                onFailure = {
                    if (it is UnknownHostException) {
                        findSingleLocalStorage(repositoryName) {
                            completionHandler(if (it == null) Result.failure(RepositoryNotFound()) else Result.success(it!!))
                        }
                    } else {
                        completionHandler(result)
                    }
                }
            )
        }, repositoryHydrator)
    }

    private fun findAllByReposUrlLocalStorage(reposUrl: String, completionHandler: (List<Repository>) -> Unit) {
        AsyncTask.execute {
            val result = appDatabase.repositoryDao().findAllByReposUrl(reposUrl)

            Handler(Looper.getMainLooper()).post {
                completionHandler(result)
            }
        }
    }

    private fun findSingleLocalStorage(repositoryName: String, completionHandler: (Repository?) -> Unit) {
        AsyncTask.execute {
            val result = appDatabase.repositoryDao().findOneByFullName(repositoryName)

            Handler(Looper.getMainLooper()).post {
                completionHandler(result)
            }
        }
    }

    fun findAllUnownedRecentVisited(limit: Int = 8, completionHandler: (List<Repository>) -> Unit) {
        AsyncTask.execute {
            val result = appDatabase.repositoryDao().findAllUnownedRecentVisited(limit, identityManager.identity?.username)

            Handler(Looper.getMainLooper()).post {
                completionHandler(result)
            }
        }
    }

    fun findAllOwnedRecentVisited(limit: Int = 8, completionHandler: (List<Repository>) -> Unit) {
        if (identityManager.identity == null) {
            completionHandler(listOf())
        } else {
            AsyncTask.execute {
                val result = appDatabase.repositoryDao().findAllOwnedRecentVisited(limit, identityManager.identity!!.username)

                Handler(Looper.getMainLooper()).post {
                    completionHandler(result)
                }
            }
        }
    }

    fun storeAllRepositoriesForCurrentUser() {
        if (identityManager.identity == null) {
            return
        }

        findAllByReposUrl("https://api.github.com/users/${identityManager.identity!!.username}/repos") {
            it.fold(
                onSuccess = {
                    it.forEach {
                        storeRepositoryToLocalStorage(it)
                        issueRepository.findAll(it.issuesUrl, {}) //this method stores all issues from the repository
                    }
                },
                onFailure = {}
            )
        }
    }

    private fun storeRepositoryToLocalStorage(repository: Repository) {
        AsyncTask.execute {
            repository.lastVisitTimestamp = (System.currentTimeMillis() / 1000).toInt() //we don't need milliseconds precision

            val repoFromDb = appDatabase.repositoryDao().findOneByFullName(repository.fullName)

            if (repoFromDb == null) {
                appDatabase.repositoryDao().insert(repository)
            } else {
                repository.uid = repoFromDb!!.uid
                appDatabase.repositoryDao().update(repository)
            }
        }
    }
}