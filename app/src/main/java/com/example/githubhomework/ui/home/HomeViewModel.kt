package com.example.githubhomework.ui.home

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.databinding.InverseMethod
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.githubhomework.entities.GitHubUser
import com.example.githubhomework.repositories.GitHubUserRepository

class HomeViewModel : ViewModel() {
    val searchText: MutableLiveData<String> = MutableLiveData()

    val searchResult: MutableLiveData<Array<GitHubUser>> = MutableLiveData()

    init {
        //je tohle ok?
        searchText.observeForever {
        }

        searchResult.value = arrayOf(GitHubUser("xxx"), GitHubUser("yyy"), GitHubUser("zzz"))
    }
}