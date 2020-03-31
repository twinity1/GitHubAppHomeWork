package com.example.githubhomework.components.lists.issues

import androidx.lifecycle.ViewModel
import com.example.githubhomework.persistence.entities.Issue

class IssueListViewModel(val entity: Issue) : ViewModel() {
    var number: String = ""
        get() = "#" + entity.number.toString()


    var onIssueShow = {}

    fun onCardSelect() {
        onIssueShow()
    }
}