package com.example.githubhomework.ui.issueform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IssueFormViewModel : ViewModel() {
    var title = MutableLiveData<String>()
    var content = MutableLiveData<String>()

    var onSubmit = {}

    fun onIssueSubmit() {
        onSubmit()
    }

    fun isValid(): Boolean
    {
        return title.value != null && content.value != null
    }
}
