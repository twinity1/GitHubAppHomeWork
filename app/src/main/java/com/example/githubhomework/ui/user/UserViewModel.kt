package com.example.githubhomework.ui.user

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubhomework.persistence.entities.Repository

class UserViewModel : ViewModel() {
    val repositoryList = MutableLiveData<List<Repository>>()

    val itemsVisibility = MutableLiveData<Int>(View.GONE)

    val noFoundTextVisibility = MutableLiveData<Int>(View.GONE)
}