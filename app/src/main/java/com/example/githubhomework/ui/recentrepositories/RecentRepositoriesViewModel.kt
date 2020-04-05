package com.example.githubhomework.ui.recentrepositories

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubhomework.persistence.entities.Repository

class RecentRepositoriesViewModel : ViewModel() {
    val itemsVisibility = MutableLiveData<Int>(View.GONE)
    val noRepositoryVisibility = MutableLiveData<Int>(View.GONE)

    val repositories = MutableLiveData<List<Repository>>()
}