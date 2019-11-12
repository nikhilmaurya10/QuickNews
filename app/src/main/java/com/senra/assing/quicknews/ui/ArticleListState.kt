package com.senra.assing.quicknews.ui

import com.senra.assing.quicknews.model.TopHeadlines

sealed class ArticleListState {
    object Loading : ArticleListState()
    class Result(val data : List<TopHeadlines.Article>) : ArticleListState()
    class Error(val error: Throwable?) : ArticleListState()
}
