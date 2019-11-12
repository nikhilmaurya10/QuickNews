package com.senra.assing.quicknews.model


import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.senra.assing.quicknews.data.Converters

data class TopHeadlines(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
) {
    @Entity(tableName = "articles")
    data class Article(
        @SerializedName("author")
        val author: String?,
        @SerializedName("content")
        val content: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("publishedAt")
        val publishedAt: String?,
        @SerializedName("source")
        @TypeConverters(Converters::class)
        val source: Source?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("url")
        @PrimaryKey
        val url: String,
        @SerializedName("urlToImage")
        val urlToImage: String?
    ) {
        data class Source(
            @SerializedName("id")
            val id: String?,
            @SerializedName("name")
            val name: String
        )
    }
}