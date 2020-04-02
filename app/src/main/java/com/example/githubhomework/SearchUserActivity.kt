package com.example.githubhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SearchUserActivity : AppCompatActivity() {
    lateinit var binding: SearchUserActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_user)
    }
}
