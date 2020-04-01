package com.example.githubhomework.tools.api

import android.os.Handler
import android.os.Looper
import com.example.githubhomework.tools.HttpClient.HttpClient
import com.example.githubhomework.tools.api.parsers.SingleResultParser
import com.google.gson.*
import okhttp3.*
import java.io.IOException
import java.lang.Exception
import java.net.URL

class ApiGetSingleRequest(private val httpClient: HttpClient) {
    var gson = Gson()

    class ApiGetSingleForbiddenException(message: String) : Exception(message)

    fun <T> getAsObject(url: String, classType: Class<T>, completionHandler: (Result<T>) -> Unit) {
        val url = URL(url)

        val request = Request.Builder().url(url).build()

        val uiHandler = Handler(Looper.getMainLooper())

        httpClient.client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                uiHandler.post {
                    completionHandler(Result.failure(e))
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body()!!.string()

                if (response.code() == 200) {
                    try {
                        val parseResult = SingleResultParser().parseJsonResult(body!!, classType)

                        uiHandler.post {
                            completionHandler(Result.success(parseResult))
                        }

                    }
                    catch (e: JsonSyntaxException) {
                        uiHandler.post {
                            completionHandler(Result.failure(e))
                        }
                    }
                } else if (response.code() == 403) {
                    val message = JsonParser().parse(body).asJsonObject["message"].asString

                    uiHandler.post {
                        completionHandler(Result.failure(
                            ApiGetSingleForbiddenException(
                                message
                            )
                        ))
                    }
                }
            }
        })
    }



}