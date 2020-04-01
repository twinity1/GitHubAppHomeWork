package com.example.githubhomework.tools.api.parsers

import com.google.gson.Gson
import com.google.gson.JsonParser

class SingleResultParser {
    var gson = Gson()

    fun <T> parseJsonResult(content: String, classType: Class<T>): T {
        val jsonElement = JsonParser().parse(content)

        val result = gson.fromJson(jsonElement, classType)

        return result
    }
}