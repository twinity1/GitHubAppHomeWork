package com.example.githubhomework.ui.home

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.githubhomework.repositories.UserRepository
import com.example.githubhomework.tools.ErrorMessageHandler

class SearchObserver(private val userRepository: UserRepository) {

    fun create(owner: HomeFragment): Observer<String> {
        val handler = Handler(Looper.getMainLooper())

        return Observer {
            handler.removeCallbacksAndMessages(null)

            if (it.trim() != "") {
                handler.postDelayed({
                    userRepository.findByName(it) {
                        it.fold(
                            onSuccess = {
                                owner.homeViewModel.searchResult.value = it
                            },

                            onFailure = {
                                Toast.makeText(owner.requireActivity(), ErrorMessageHandler().getStringByException(it, owner.requireActivity().resources), Toast.LENGTH_LONG).show()
                            }
                        )
                    }
                }, 300)
            } else {
                owner.homeViewModel.searchResult.value = listOf()
            }
        }
    }
}