package com.senra.assing.quicknews.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.senra.assing.quicknews.data.Converters
import com.senra.assing.quicknews.model.TopHeadlines.Article

@Database(entities = [Article::class],version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NewsDB : RoomDatabase() {
    abstract fun topHeadlinesDao() : TopHeadlinesDao
}