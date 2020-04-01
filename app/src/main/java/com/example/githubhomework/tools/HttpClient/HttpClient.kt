package com.example.githubhomework.tools.HttpClient

import okhttp3.OkHttpClient

class HttpClient(private val basicCredentialsInterceptor: BasicCredentialsInterceptor) {

    val client: OkHttpClient by lazy {
        OkHttpClient().newBuilder().addInterceptor(basicCredentialsInterceptor).build()
    }
}