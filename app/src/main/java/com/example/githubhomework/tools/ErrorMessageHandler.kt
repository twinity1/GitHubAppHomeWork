package com.example.githubhomework.tools

import android.content.res.Resources
import com.example.githubhomework.R
import java.net.UnknownHostException

class ErrorMessageHandler {
    fun getStringByException(e: Throwable): String {
        if (e is UnknownHostException) {
            return Resources.getSystem().getString(R.string.network_error)
        }

        if (e is ApiGetSingleRequest.ApiGetSingleForbiddenException && e.message != null) {
            return e.message!!
        }

        return Resources.getSystem().getString(R.string.unknown_error)
    }
}