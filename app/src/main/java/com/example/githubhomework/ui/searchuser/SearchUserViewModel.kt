package com.example.githubhomework.ui.searchuser

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubhomework.persistence.entities.User

class SearchUserViewModel : ViewModel() {
    val searchText: MutableLiveData<String> = MutableLiveData()

    val searchResult: MutableLiveData<List<User>> = MutableLiveData()
}