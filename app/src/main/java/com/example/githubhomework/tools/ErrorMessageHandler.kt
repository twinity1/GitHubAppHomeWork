package com.example.githubhomework.tools

import com.example.githubhomework.R
import java.net.UnknownHostException

class ErrorMessageHandler {
    fun getStringIdByException(e: Throwable): Int {
        if (e is UnknownHostException) {
            return R.string.network_error
        }

        return R.string.unknown_error
    }
}