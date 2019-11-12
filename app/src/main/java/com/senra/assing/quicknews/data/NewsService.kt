package com.senra.assing.quicknews.data

import com.senra.assing.quicknews.data.local.NewsDB
import com.senra.assing.quicknews.data.remote.NewsAPI
import com.senra.assing.quicknews.model.TopHeadlines

open class NewsService (
    private val database: NewsDB,
    private val api: NewsAPI
                        ) : NewsRepository {
    override suspend fun getTopHeadlinesCached(): List<TopHeadlines.Article> {
        return database.topHeadlinesDao().getAllArticles()
    }

    override suspend fun getTopHeadlines(): List<TopHeadlines.Article> {
        return api.getTopHeadlinesAsync().await().articles.also {
            database.topHeadlinesDao().deleteAllArticles()
            database.topHeadlinesDao().insertTopArtilces(it)
        }

    }

}