package com.example.githubhomework.ui.home

import android.os.Handler
import android.os.Looper
import androidx.core.os.postDelayed
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubhomework.entities.GitHubUser
import com.example.githubhomework.repositories.GitHubRepositoryRepository
import com.example.githubhomework.repositories.GitHubUserRepository

class HomeViewModel : ViewModel() {
    val searchText: MutableLiveData<String> = MutableLiveData()

    val searchResult: MutableLiveData<List<GitHubUser>> = MutableLiveData()
}