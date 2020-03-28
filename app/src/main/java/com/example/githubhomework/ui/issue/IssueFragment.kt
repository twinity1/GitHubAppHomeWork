package com.example.githubhomework.ui.issue

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.githubhomework.R
import com.example.githubhomework.databinding.FragmentIssueBinding
import com.example.githubhomework.repositories.IssueRepository
import com.example.githubhomework.tools.ErrorMessageHandler
import io.noties.markwon.Markwon
import kotlinx.android.synthetic.main.fragment_issue.*
import kotlinx.android.synthetic.main.fragment_repository.*

class IssueFragment : Fragment() {
    lateinit var issueUrl: String

    lateinit var viewModel: IssueViewModel

    lateinit var binding: FragmentIssueBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(IssueViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_issue, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        var toolbar = (this.activity as AppCompatActivity).supportActionBar

        toolbar?.title = ""

        viewModel.entity.observe(this, Observer {
            toolbar?.title = "Issue ${it.title}"
        })

        IssueRepository.shared.findSingle(issueUrl) {
            it.fold(
                onSuccess = {
                    viewModel.entity.value = it

                    Markwon.create(activity!!).setMarkdown(issueBody, it.body)
                },
                onFailure =  {
                    Toast.makeText(activity, ErrorMessageHandler().getStringByException(it, activity!!.resources), Toast.LENGTH_SHORT).show()
                    activity!!.finish()
                }
            )
        }
    }
}
