package com.example.githubhomework

import android.app.Application
import com.example.githubhomework.repositories.IssueRepository
import com.example.githubhomework.repositories.RepositoryRepository
import com.example.githubhomework.repositories.UserRepository
import com.example.githubhomework.tools.ApiGetMultipleRequest
import com.example.githubhomework.tools.ApiGetSingleRequest
import com.example.githubhomework.tools.HttpClient.HttpClient
import com.example.githubhomework.ui.home.RecycleViewObserver
import com.example.githubhomework.ui.home.SearchObserver
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

val myModule = module {
    single { IssueRepository(get(), get()) }
    single { RepositoryRepository(get()) }
    single { UserRepository(get()) }
    single { SearchObserver(get()) }
    single { RecycleViewObserver() }
    single { HttpClient() }
    factory { ApiGetSingleRequest(get()) }
    factory { ApiGetMultipleRequest(get()) }
}

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(myModule)
        }
    }
}