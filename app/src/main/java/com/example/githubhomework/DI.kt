package com.example.githubhomework

import com.example.githubhomework.persistence.repositories.IssueRepository
import com.example.githubhomework.persistence.repositories.RepositoryRepository
import com.example.githubhomework.persistence.repositories.UserRepository
import com.example.githubhomework.tools.ApiGetMultipleRequest
import com.example.githubhomework.tools.ApiGetSingleRequest
import com.example.githubhomework.tools.HttpClient.BasicCredentialsInterceptor
import com.example.githubhomework.tools.HttpClient.HttpClient
import com.example.githubhomework.tools.Identity.IdentityManager
import com.example.githubhomework.ui.home.RecycleViewObserver
import com.example.githubhomework.ui.home.SearchObserver
import com.example.githubhomework.ui.profilecontent.ProfileContentViewModel
import com.example.githubhomework.ui.signin.SignInViewModel
import com.example.githubhomework.ui.repository.IssueObserver
import com.example.githubhomework.ui.repository.LabelObserver
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val myModule = module {
    //repositories
    single { IssueRepository(get(), get(), get()) }
    single { RepositoryRepository(get()) }
    single { UserRepository(get()) }

    //tools
    single { HttpClient(get()) }
    single { BasicCredentialsInterceptor() }
    single(null, true) { IdentityManager(get(), get(), get()) }

    //api
    factory { ApiGetSingleRequest(get()) }
    factory { ApiGetMultipleRequest(get()) }

    //home screen
    single { SearchObserver(get()) }
    single { RecycleViewObserver() }

    //repository screen
    single { LabelObserver() }
    single { IssueObserver() }

    //view models
    viewModel { SignInViewModel(get()) }
    viewModel { ProfileContentViewModel(get()) }
}
