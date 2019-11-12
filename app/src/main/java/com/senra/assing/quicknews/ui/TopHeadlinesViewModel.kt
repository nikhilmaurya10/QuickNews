package com.senra.assing.quicknews.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.senra.assing.quicknews.BaseObservableViewModel
import com.senra.assing.quicknews.DispatcherProvider
import com.senra.assing.quicknews.data.NewsRepository
import com.senra.assing.quicknews.model.TopHeadlines
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TopHeadlinesViewModel(
    private val newsRepository: NewsRepository,
    private val dispatcher: DispatcherProvider
) : BaseObservableViewModel() {
    private val state = MutableLiveData<ArticleListState>().apply {
        value = ArticleListState.Loading
    }
    val articleList: LiveData<List<TopHeadlines.Article>?> = Transformations.map(state) {
            state -> (state as? ArticleListState.Result)?.data
    }

    val showLoading: LiveData<Boolean> = Transformations.map(state) {
            state -> state is ArticleListState.Loading
    }

    val showError: LiveData<Boolean> = Transformations.map(state) {
            state -> state is ArticleListState.Error
    }

    val showData: LiveData<Boolean> = Transformations.map(state) { state ->
        state is ArticleListState.Result
    }

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            startLoading()
            val cachedData = newsRepository.getTopHeadlinesCached()
            if (cachedData != null && cachedData.isNotEmpty()) {
                setState(ArticleListState.Result(cachedData))
            }

            val newState = withContext(dispatcher.IO) {
                try {
                    val response = newsRepository.getTopHeadlines()
                    return@withContext ArticleListState.Result(response)
                } catch (error: Throwable) {
                    return@withContext ArticleListState.Error(error)
                }
            }

            setState(newState)
        }
    }

    private fun startLoading() {
        setState(ArticleListState.Loading)
    }
    private fun setState(newState: ArticleListState) {
        this.state.value = newState
        notifyChange()
    }
}