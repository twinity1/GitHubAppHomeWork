package com.example.githubhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView

class SearchUserActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    lateinit var binding: SearchUserActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_user)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
