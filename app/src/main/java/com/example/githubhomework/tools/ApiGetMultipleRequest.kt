package com.example.githubhomework.tools

import android.os.Handler
import android.os.Looper
import com.google.gson.*
import okhttp3.*
import java.io.IOException
import java.lang.IllegalStateException
import java.net.URL

class ApiGetMultipleRequest {
    var gson = Gson()

    var onJsonParse: ((JsonElement) -> JsonArray)? = null

    private var httpClient = OkHttpClient()

    fun <T> getAsList(url: String, classType: Class<T>, completionHandler: (Result<List<T>>) -> Unit) {
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
            }
        })
    }


    private fun <T> parseJsonResult(content: String, classType: Class<T>): List<T> {
        val list = ArrayList<T>()

        var jsonElement = JsonParser().parse(content)

        var jsonArray: JsonArray? = null

        if (onJsonParse != null) {
            jsonArray = onJsonParse!!(jsonElement)
        } else {
            jsonArray = jsonElement.asJsonArray
        }

        for (el in jsonArray!!) {
            list.add(gson.fromJson(el, classType))
        }

        return list
    }
}