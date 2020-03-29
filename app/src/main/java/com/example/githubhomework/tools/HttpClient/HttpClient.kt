package com.example.githubhomework.tools.HttpClient

import okhttp3.OkHttpClient

class HttpClient(private val basicInterceptor: BasicInterceptor) {

    val client: OkHttpClient by lazy {
        OkHttpClient().newBuilder().addInterceptor(basicInterceptor).build()
    }
}