package com.example.githubhomework.persistence.repositories

import android.os.AsyncTask
import com.example.githubhomework.persistence.AppDatabase
import com.example.githubhomework.persistence.entities.Repository
import com.example.githubhomework.tools.api.ApiGetMultipleRequest
import com.example.githubhomework.tools.api.ApiGetSingleRequest

class RepositoryRepository(private val multipleRequest: ApiGetMultipleRequest, private val singleRequest: ApiGetSingleRequest, private val appDatabase: AppDatabase) {
    fun findAllByReposUrl(reposUrl: String, completionHandler: (Result<List<Repository>>) -> Unit) {
        multipleRequest.getAsList(reposUrl, Repository::class.java, {
            completionHandler(it)
        })
    }

    fun findSingle(repositoryName: String, completionHandler: (Result<Repository>) -> Unit) {
        singleRequest.getAsObject("https://api.github.com/repos/${repositoryName}", Repository::class.java) {
            completionHandler(it)

            it.fold(
                onSuccess = {
                    AsyncTask.execute {
                        storeRepositoryToLocalStorage(it)
                    }
                },
                onFailure = {}
            )
        }
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