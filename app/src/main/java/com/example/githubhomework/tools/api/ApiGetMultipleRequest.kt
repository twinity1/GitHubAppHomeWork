package com.example.githubhomework.tools.api

import android.os.Handler
import android.os.Looper
import com.example.githubhomework.tools.HttpClient.HttpClient
import com.google.gson.*
import okhttp3.*
import java.io.IOException
import java.lang.Exception
import java.lang.IllegalStateException
import java.net.URL

class ApiGetMultipleRequest(private val httpClient: HttpClient) {
    var gson = Gson()

    var onJsonParse: ((JsonElement) -> JsonArray)? = null

    class ApiGetMultipleForbiddenException(message: String) : Exception(message)

    fun <T> getAsList(url: String, classType: Class<T>, completionHandler: (Result<List<T>>) -> Unit, elementHydrator: ((JsonElement, T) -> Unit)? = null) {
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
                        val parseResult = parseJsonResult(body!!, classType, elementHydrator)

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
                    uiHandler.post {
                        val message = JsonParser().parse(body).asJsonObject["message"].asString

                        completionHandler(Result.failure(
                            ApiGetMultipleForbiddenException(
                                message
                            )
                        ))
                    }
                }
            }
        })
    }


    private fun <T> parseJsonResult(content: String, classType: Class<T>, elementAdd: ((JsonElement, T) -> Unit)? = null): List<T> {
        val list = ArrayList<T>()

        var jsonElement = JsonParser().parse(content)

        var jsonArray: JsonArray? = null

        if (onJsonParse != null) {
            jsonArray = onJsonParse!!(jsonElement)
        } else {
            jsonArray = jsonElement.asJsonArray
        }

        for (el in jsonArray!!) {
             val entity = gson.fromJson(el, classType)

            elementAdd?.invoke(el, entity)

            list.add(entity)
        }

        return list
    }
}