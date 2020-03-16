package com.example.githubhomework.ui.repository

import androidx.lifecycle.*
import com.example.githubhomework.entities.Issue
import com.example.githubhomework.entities.Label

class RepositoryViewModel : ViewModel() {
    val issueList = MutableLiveData<List<Issue>>()

    var selectedLabels = MutableLiveData<List<Label>>()

    var labelList = MutableLiveData<List<Label>>()
}