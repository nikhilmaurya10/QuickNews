package com.senra.assing.quicknews.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.senra.assing.quicknews.model.TopHeadlines.Article.Source

class Converters {

    @TypeConverter
    fun getSource(value: Source?): String {
        value?.let {
            return Gson().toJson(value)
        }
        return ""
    }
    @TypeConverter
    fun setSource(source: String): Source? {
        if (source.isEmpty()) return null
        return Gson().fromJson(source, Source::class.java)
    }

}