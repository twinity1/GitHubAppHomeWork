package com.example.githubhomework.persistence.entities

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


data class Label(
    val url: String = "",

    val name: String = "",

    val color: String = "",

    val description: String = ""
)