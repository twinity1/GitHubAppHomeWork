package com.example.githubhomework.ui.issueform

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.githubhomework.R
import com.example.githubhomework.databinding.IssueFormFragmentBinding

class IssueFormFragment : Fragment() {

    companion object {
        fun newInstance() = IssueFormFragment()
    }

    private lateinit var viewModel: IssueFormViewModel
    private lateinit var binding: IssueFormFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(IssueFormViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.issue_form_fragment, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}
