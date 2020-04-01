package com.example.githubhomework.ui.profilecontent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubhomework.tools.Identity.IdentityManager

class ProfileContentViewModel(private val identityManager: IdentityManager) : ViewModel() {
    var username = MutableLiveData<String>()

    var onSignOut = {}

    fun signOut() {
        identityManager.signOut {
            onSignOut()
        }
    }
}