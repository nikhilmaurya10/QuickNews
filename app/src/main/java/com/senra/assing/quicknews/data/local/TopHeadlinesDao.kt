package com.senra.assing.quicknews.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.senra.assing.quicknews.model.TopHeadlines.Article

@Dao
interface TopHeadlinesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopArtilces(topHeadlinesArticles: List<Article>)

    @Query("SELECT * FROM articles")
    suspend fun getAllArticles(): List<Article>

    @Query("DELETE from articles")
    suspend fun deleteAllArticles() : Int


    @Query("SELECT * FROM articles WHERE url = :url")
    suspend fun getArticleByURL(url: String): Article


}