package com.example.githubhomework.ui.repository

import androidx.lifecycle.*
import com.example.githubhomework.entities.Issue
import com.example.githubhomework.entities.Label
import com.example.githubhomework.entities.helpers.LabelExtractor

class RepositoryViewModel : ViewModel() {
    var issueData: List<Issue> = ArrayList()
        set(value) {
            field = value
            
            issueList.value = value
            labelList.value = LabelExtractor.extractFromIssueList(value)
//            selectedLabels.value = ArrayList()
        }

    val issueList = MutableLiveData<List<Issue>>()

    var selectedLabels = MutableLiveData<HashMap<Label, Boolean>>()

    var labelList = MutableLiveData<List<Label>>()

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
}