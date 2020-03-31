package com.example.githubhomework.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubhomework.persistence.entities.User

class HomeViewModel : ViewModel() {
    val searchText: MutableLiveData<String> = MutableLiveData()

    val searchResult: MutableLiveData<List<User>> = MutableLiveData()
}