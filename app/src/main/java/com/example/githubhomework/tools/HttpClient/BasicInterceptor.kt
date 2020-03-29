package com.example.githubhomework.tools.HttpClient

import com.example.githubhomework.tools.Identity.IdentityManager
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class BasicInterceptor(val identityManager: IdentityManager): Interceptor
{
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            if (identityManager.identity == null) {
                return chain.proceed(chain.request().newBuilder().build())
            }

            val request: Request = chain.request()
            val authenticatedRequest = request.newBuilder().header("Authorization", Credentials.basic(identityManager.identity!!.username, identityManager.identity!!.password)).build()
            return chain.proceed(authenticatedRequest)
        }
}