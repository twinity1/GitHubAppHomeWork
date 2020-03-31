package com.example.githubhomework.tools

import android.content.res.Resources
import com.example.githubhomework.R
import com.example.githubhomework.tools.Identity.IdentityManager
import java.net.UnknownHostException

class ErrorMessageHandler {
    fun getStringByException(e: Throwable, resources: Resources): String {
        if (e is UnknownHostException) {
            return resources.getString(R.string.network_error)
        }

        if ((e is ApiGetSingleRequest.ApiGetSingleForbiddenException && e.message != null) || (e is ApiGetMultipleRequest.ApiGetMultipleForbiddenException && e.message != null)) {
            return e.message!!
        }

        if (e is IdentityManager.BadCredentials && e.message != null) {
            return e.message!!
        }

        return resources.getString(R.string.unknown_error)
    }
}