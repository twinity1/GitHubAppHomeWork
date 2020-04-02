package com.example.githubhomework.ui.searchuser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.githubhomework.R
import com.example.githubhomework.databinding.FragmentSearchUserBinding
import org.koin.android.ext.android.inject

class SearchUserFragment : Fragment() {
    lateinit var viewModel: SearchUserViewModel
    lateinit var binding: FragmentSearchUserBinding

    private val searchObserver: SearchObserver by inject()
    private val recycleViewObserver: RecycleViewObserver by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(SearchUserViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_user, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.searchText.observe(viewLifecycleOwner, searchObserver.create(this))
        viewModel.searchResult.observe(viewLifecycleOwner, recycleViewObserver.create(this))
    }
}