package com.example.githubhomework.tools

import android.os.Handler
import android.os.Looper
import com.google.gson.*
import okhttp3.*
import java.io.IOException
import java.lang.Exception
import java.lang.IllegalStateException
import java.net.URL

class ApiGetSingleRequest {
    var gson = Gson()

    private var httpClient = OkHttpClient()

    class ApiGetSingleForbiddenException(message: String) : Exception(message)

    fun <T> getAsObject(url: String, classType: Class<T>, completionHandler: (Result<T>) -> Unit) {
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

                if (response.code() == 200) {
                    try {
                        val parseResult = parseJsonResult(body!!, classType)

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
                } else if (response.code() == 403) {
                    val message = JsonParser().parse(body).asJsonObject["message"].asString

                    uiHandler.post {
                        completionHandler(Result.failure(ApiGetSingleForbiddenException(message)))
                    }
                }
            }
        })
    }


    private fun <T> parseJsonResult(content: String, classType: Class<T>): T {
        val jsonElement = JsonParser().parse(content)

        val result = gson.fromJson(jsonElement, classType)

        return result
    }
}