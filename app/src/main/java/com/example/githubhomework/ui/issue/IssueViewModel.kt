package com.example.githubhomework.ui.issue

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubhomework.entities.Issue

class IssueViewModel : ViewModel() {
    var entity = MutableLiveData<Issue>()
}