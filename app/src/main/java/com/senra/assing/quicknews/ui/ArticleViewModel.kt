package com.senra.assing.quicknews.ui

import androidx.databinding.BaseObservable
import com.senra.assing.quicknews.model.TopHeadlines.*

class ArticleViewModel : BaseObservable() {

    var article : Article? = null
        set(value) {
            field = value
            notifyChange()
        }

    val author : String
        get() = article?.author.orEmpty()

    val title : String
        get() = article?.title.orEmpty()

    val description : String
        get() = article?.description.orEmpty()

    val publishedAt : String
        get() = article?.publishedAt.orEmpty()

    val source : String
        get() = article?.source?.name.orEmpty()

    val urlToImage : String
        get() = article?.urlToImage.orEmpty()

    val url : String
        get() = article?.url.orEmpty()


}