package com.example.githubhomework.tools

import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException
import java.lang.IllegalStateException
import java.net.URL

class ApiGetRequest {
    var gson = Gson()

    private var httpClient = OkHttpClient()

    fun <T> send(url: String, completionHandler: (Result<T>) -> Unit) {
        val url = URL(url)

        val request = Request.Builder().url(url).build()

        val uiHandler = Handler(Looper.getMainLooper())

        httpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                uiHandler.post {
                    completionHandler(Result.failure(e))
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body()!!.string()

                try {
                    val parseResult = parseJsonResult<T>(body!!)

                    uiHandler.post {
                        completionHandler(Result.success(parseResult))
                    }

                } catch (e: IllegalStateException) {
                    uiHandler.post {
                        completionHandler(Result.failure(e))
                    }
                } catch (e: JsonSyntaxException) {
                    uiHandler.post {
                        completionHandler(Result.failure(e))
                    }
                }
            }
        })
    }


    private fun <T> parseJsonResult(content: String): T {
        val value = object : TypeToken<T>() {}

        val result = gson.fromJson<T>(content, value.type)

        return result
    }
}