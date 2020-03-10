package com.example.githubhomework.repositories

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.githubhomework.entities.GitHubUser
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException
import java.lang.IllegalStateException
import java.lang.reflect.Type
import java.net.URL
import java.net.URLEncoder

class GitHubUserRepository {
    private val baseUserUrl = "https://api.github.com/search/users?q="

    private val httpClient = OkHttpClient()

    companion object {
        val shared = GitHubUserRepository()
    }

    fun findAllByName(name: String, completionHandler: (List<GitHubUser>?) -> Unit) {
        val s = baseUserUrl + URLEncoder.encode( name, "UTF-8")

        val url = URL(s)

        val request = Request.Builder().url(url).build()

        val uiHandler = Handler(Looper.getMainLooper())
        val completeWithNullResult = {uiHandler.post { completionHandler(null) }}

        httpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                uiHandler.post {
                    completionHandler(null)
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()

                if (body == null) {
                    completeWithNullResult()
                    return
                }

                Log.i("INFO", body)

                try {
                    val list = parseJsonResult(body!!)

                    uiHandler.post {
                        completionHandler(list)
                    }

                } catch (e: IllegalStateException) {
                    completeWithNullResult()
                } catch (e: JsonSyntaxException) {
                    completeWithNullResult()
                }

            }
        })
    }

    private fun parseJsonResult(content: String): List<GitHubUser> {
        val value = object : TypeToken<List<GitHubUser>>() {}


        val gson = GsonBuilder()
            .registerTypeAdapter(value.type, object : JsonDeserializer<List<GitHubUser>> {
                override fun deserialize(
                    json: JsonElement?,
                    typeOfT: Type?,
                    context: JsonDeserializationContext?
                ): List<GitHubUser> {
                    val items = json!!.asJsonObject.get("items")

                    return Gson().fromJson<List<GitHubUser>>(items, value.type)
                }
            })
            .create()


        val result = gson.fromJson<List<GitHubUser>>(content, value.type)

        return result
    }
}