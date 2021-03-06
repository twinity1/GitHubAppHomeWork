package com.example.githubhomework.ui.searchuser

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.githubhomework.R
import com.example.githubhomework.databinding.FragmentSearchUserBinding
import com.example.githubhomework.tools.ui.addDivider
import kotlinx.android.synthetic.main.fragment_search_user.*
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

        searchRecycleView.addDivider(requireContext())

        viewModel.searchText.observe(viewLifecycleOwner, searchObserver.create(this))
        viewModel.searchResult.observe(viewLifecycleOwner, recycleViewObserver.create(this))

        showKeyboard(searchUserInput)
    }

    private fun showKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        }
    }
}