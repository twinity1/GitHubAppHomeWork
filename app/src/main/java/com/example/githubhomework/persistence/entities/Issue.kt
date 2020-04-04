package com.example.githubhomework.persistence.entities

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

@Entity
data class Issue(
    @PrimaryKey
    var uid: Int? = null,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "body")
    var body: String,

    @ColumnInfo(name = "owner")
    var owner: String = "",

    @ColumnInfo(name = "repositoryName")
    var repositoryName: String = "",

    @ColumnInfo(name = "number")
    var number: Int = 0,

    @ColumnInfo(name = "url")
    var url: String = "",

    @TypeConverters(LabelDataConverter::class)
    var labels: List<Label> = listOf()
)
{
    val numberWithHash
        get() = "#" + number.toString()
}


class LabelDataConverter {
    @TypeConverter
    fun fromLabels(labels: List<Label>): String? {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Label>>() {}.type
        return gson.toJson(labels, type)
    }

    @TypeConverter
    fun toLabels(labelString: String?): List<Label> {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Label>>() {}.type
        return gson.fromJson<List<Label>>(labelString, type)
    }
}