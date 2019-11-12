package com.senra.assing.quicknews.data.remote

import com.senra.assing.quicknews.model.TopHeadlines
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface NewsAPI {
    @GET("top-headlines?country=us&apiKey=dfdcc57a6bd64e0bb42e2ae1f32e9f1e")
    fun getTopHeadlinesAsync(): Deferred<TopHeadlines>
}
