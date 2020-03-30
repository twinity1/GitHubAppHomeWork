package com.example.githubhomework.ui.issueform

import androidx.lifecycle.ViewModel

class IssueFormViewModel : ViewModel() {
    var submitBtnTitle = ""

    var title = ""
    var content = ""

    var onSubmit = {}

    fun onIssueSubmit() {
        onSubmit()
    }
}
