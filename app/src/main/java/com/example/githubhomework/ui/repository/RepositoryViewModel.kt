package com.example.githubhomework.ui.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubhomework.entities.Issue

class RepositoryViewModel : ViewModel() {
    var issueList = MutableLiveData<List<Issue>>()
}