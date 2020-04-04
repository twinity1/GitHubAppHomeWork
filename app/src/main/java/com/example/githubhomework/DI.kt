package com.example.githubhomework

import com.example.githubhomework.persistence.repositories.IssueRepository
import com.example.githubhomework.persistence.repositories.RepositoryRepository
import com.example.githubhomework.persistence.repositories.UserRepository
import com.example.githubhomework.tools.api.ApiGetMultipleRequest
import com.example.githubhomework.tools.api.ApiGetSingleRequest
import com.example.githubhomework.tools.HttpClient.BasicCredentialsInterceptor
import com.example.githubhomework.tools.HttpClient.HttpClient
import com.example.githubhomework.tools.Identity.IdentityManager
import com.example.githubhomework.ui.searchuser.RecycleViewObserver
import com.example.githubhomework.ui.searchuser.SearchObserver
import com.example.githubhomework.ui.issueform.IssueFormSubmit
import com.example.githubhomework.ui.issueform.IssueFormViewModel
import com.example.githubhomework.ui.profilecontent.ProfileContentViewModel
import com.example.githubhomework.ui.recentrepositories.RecentRepositoriesViewModel
import com.example.githubhomework.ui.signin.SignInViewModel
import com.example.githubhomework.ui.repository.IssueObserver
import com.example.githubhomework.ui.repository.LabelObserver
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val myModule = module {
    //repositories
    single { IssueRepository(get(), get(), get(), get()) }
    single { RepositoryRepository(get(), get(), get(), get()) }
    single { UserRepository(get(), get()) }

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

    //issue form screen
    single { IssueFormSubmit(get()) }

    //repository screen
    single { LabelObserver() }
    single { IssueObserver() }

    //view models
    viewModel { SignInViewModel(get()) }
    viewModel { IssueFormViewModel() }
    viewModel { ProfileContentViewModel(get()) }
    viewModel { RecentRepositoriesViewModel() }
}
