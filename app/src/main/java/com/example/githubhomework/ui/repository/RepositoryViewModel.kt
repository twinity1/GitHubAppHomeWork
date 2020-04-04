package com.example.githubhomework.ui.repository

import androidx.lifecycle.*
import com.example.githubhomework.persistence.entities.Issue
import com.example.githubhomework.persistence.entities.Label
import com.example.githubhomework.persistence.entities.Repository
import com.example.githubhomework.persistence.entities.helpers.LabelExtractor

class RepositoryViewModel : ViewModel() {
    var onNewIssueShow = {}

    //recycle view
    var issueData: List<Issue> = ArrayList()
        set(value) {
            field = value
            
            issueList.value = value
            labelList.value = LabelExtractor.extractFromIssueList(value)
        }

    val issueList = MutableLiveData<List<Issue>>()
    var selectedLabels = MutableLiveData<HashMap<Label, Boolean>>()
    var labelList = MutableLiveData<List<Label>>()
    //end recycle view


    //repository
    val repository = MutableLiveData<Repository>()

    fun filterIssues() {
        val selectedLabelsList = selectedLabels.value!!.filterValues { it == true }.keys

        var resultList = issueData

        if (selectedLabelsList.count() != 0) {
            resultList = issueData.filter {
                it.labels.any { selectedLabelsList.contains(it) }
            }
        }

        if (resultList != issueList.value) {
            issueList.value = resultList
        }
    }

    fun onNewIssueAdd() {
      onNewIssueShow.invoke()
    }
}