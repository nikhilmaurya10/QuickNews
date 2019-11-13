package com.senra.assing.quicknews.di.modules

import dagger.Provides
import android.app.Application
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.senra.assing.quicknews.App
import com.senra.assing.quicknews.data.NewsService
import com.senra.assing.quicknews.data.local.NewsDB
import com.senra.assing.quicknews.data.remote.NewsAPI
import dagger.Module
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val mApplication: Application) {


    @Singleton
    @Provides
    fun getRetrofit(client: OkHttpClient) : Retrofit {
            return Retrofit.Builder()
                .baseUrl((mApplication as App).baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(client)
                .build()
    }

    @Provides
    fun getNewService(database: NewsDB, api: NewsAPI) : NewsService {
        return NewsService(database, api)
    }

    @Provides
    fun getNewsApi(retrofit : Retrofit): NewsAPI {
        return retrofit.create(NewsAPI::class.java)
    }

    @Provides
    fun getOkHttpClient(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Singleton
    @Provides
    fun getNewsDb() : NewsDB{
        return Room.databaseBuilder(mApplication, NewsDB::class.java,"news.db")
            .allowMainThreadQueries().build()
    }

}