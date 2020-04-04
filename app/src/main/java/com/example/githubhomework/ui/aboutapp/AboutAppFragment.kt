package com.example.githubhomework.ui.aboutapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.githubhomework.R
import com.example.githubhomework.databinding.FragmentAboutAppBinding

class AboutAppFragment : Fragment() {

    private lateinit var viewModel: AboutAppViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(AboutAppViewModel::class.java)
        val binding: FragmentAboutAppBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_about_app, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }
}