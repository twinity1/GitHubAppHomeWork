package com.example.githubhomework.ui.githubuser

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubhomework.persistence.entities.Repository

class UserViewModel : ViewModel() {
    var repositoryList = MutableLiveData<List<Repository>>()
}