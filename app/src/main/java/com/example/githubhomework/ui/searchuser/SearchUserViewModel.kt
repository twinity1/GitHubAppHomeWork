package com.example.githubhomework.ui.searchuser

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubhomework.persistence.entities.User

class SearchUserViewModel : ViewModel() {
    val searchText: MutableLiveData<String> = MutableLiveData()

    val searchResult: MutableLiveData<List<User>> = MutableLiveData()

    val itemsVisibility = MutableLiveData<Int>(View.GONE)
    val noFoundTextVisibility = MutableLiveData<Int>(View.GONE)
}