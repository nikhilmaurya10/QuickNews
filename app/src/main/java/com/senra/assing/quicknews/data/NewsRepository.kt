package com.senra.assing.quicknews.data

import com.senra.assing.quicknews.model.TopHeadlines.*

interface NewsRepository {
    suspend fun getTopHeadlines() : List<Article>
    suspend fun getTopHeadlinesCached(): List<Article>
}