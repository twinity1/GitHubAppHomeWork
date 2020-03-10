package com.example.githubhomework.ui.home

import android.os.Handler
import android.os.Looper
import androidx.core.os.postDelayed
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubhomework.entities.GitHubUser
import com.example.githubhomework.repositories.GitHubUserRepository

class HomeViewModel : ViewModel() {
    val searchText: MutableLiveData<String> = MutableLiveData()

    val searchResult: MutableLiveData<List<GitHubUser>> = MutableLiveData()

    private val handler = Handler(Looper.getMainLooper())

    init {
        //je tohle ok?
        searchText.observeForever {
            handler.removeCallbacksAndMessages(null)

            handler.postDelayed({
                GitHubUserRepository.shared.findAllByName(it) {
                    if (it != null) {
                        searchResult.value = it!!
                    } else {
                        searchResult.value = listOf()
                    }
                }
            }, 300)
        }

//        searchResult.value = arrayOf(GitHubUser("xxx"), GitHubUser("yyy"), GitHubUser("zzz"))
    }
}