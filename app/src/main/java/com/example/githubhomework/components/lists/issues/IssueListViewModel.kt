package com.example.githubhomework.components.lists.issues

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubhomework.persistence.entities.Issue

class IssueListViewModel(val entity: Issue) : ViewModel() {
    var number: String = ""
        get() = "#" + entity.number.toString()

    val editVisibility = MutableLiveData<Int>(View.GONE)
    val deleteVisibility = MutableLiveData<Int>(View.GONE)

    var onIssueShow = {}
    var onIssueEdit = {}
    var onIssueDelete = {}

    fun cardSelect() {
        onIssueShow()
    }

    fun delete() {
        onIssueDelete()
    }

    fun edit() {
        onIssueEdit()
    }
}