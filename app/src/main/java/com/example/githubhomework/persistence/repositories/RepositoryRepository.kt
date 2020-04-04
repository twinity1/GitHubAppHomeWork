package com.example.githubhomework.persistence.repositories

import android.os.AsyncTask
import android.util.Log
import com.example.githubhomework.persistence.AppDatabase
import com.example.githubhomework.persistence.entities.Repository
import com.example.githubhomework.persistence.entities.User
import com.example.githubhomework.tools.api.ApiGetMultipleRequest
import com.example.githubhomework.tools.api.ApiGetSingleRequest
import com.google.gson.JsonElement

class RepositoryRepository(private val multipleRequest: ApiGetMultipleRequest, private val singleRequest: ApiGetSingleRequest, private val appDatabase: AppDatabase) {

    private val repositoryHydrator: ((JsonElement, Repository) -> Unit) = { jsonElement, repository ->
        repository.ownerLogin = jsonElement.asJsonObject.get("owner").asJsonObject.get("login").asString
        repository.ownerReposUrl = jsonElement.asJsonObject.get("owner").asJsonObject.get("repos_url").asString
    }

    fun findAllByReposUrl(reposUrl: String, completionHandler: (Result<List<Repository>>) -> Unit) {
        multipleRequest.getAsList(reposUrl, Repository::class.java, {
            completionHandler(it)
        }, repositoryHydrator)
    }

    fun findSingle(repositoryName: String, completionHandler: (Result<Repository>) -> Unit) {
        singleRequest.getAsObject("https://api.github.com/repos/${repositoryName}", Repository::class.java, {
            completionHandler(it)

            it.fold(
                onSuccess = {
                    AsyncTask.execute {
                        storeRepositoryToLocalStorage(it)
                    }
                },
                onFailure = {}
            )
        }, repositoryHydrator)
    }

    private fun storeRepositoryToLocalStorage(repository: Repository) {
        val repoFromDb = appDatabase.repositoryDao().findOneByFullName(repository.fullName)

        if (repoFromDb == null) {
            appDatabase.repositoryDao().insert(repository)
        } else {
            repository.uid = repoFromDb!!.uid
            appDatabase.repositoryDao().update(repository)
        }
    }
}