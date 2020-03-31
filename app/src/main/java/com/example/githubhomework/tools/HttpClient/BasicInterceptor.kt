package com.example.githubhomework.tools.HttpClient

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class BasicInterceptor: Interceptor
{
        var username: String? = null
        var password: String? = null

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            if (username == null || password == null) {
                return chain.proceed(chain.request().newBuilder().build())
            }

            val request: Request = chain.request()
            val authenticatedRequest = request.newBuilder().header("Authorization", Credentials.basic(username!!, password!!)).build()
            return chain.proceed(authenticatedRequest)
        }
}