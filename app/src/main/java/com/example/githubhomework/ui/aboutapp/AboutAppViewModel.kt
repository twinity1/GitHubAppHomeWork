package com.example.githubhomework.ui.aboutapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutAppViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is aboutapp Fragment"
    }
    val text: LiveData<String> = _text
}