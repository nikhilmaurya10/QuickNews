package com.senra.assing.quicknews

import com.senra.assing.quicknews.data.NewsService
import com.senra.assing.quicknews.model.TopHeadlines
import com.senra.assing.quicknews.ui.TopHeadlinesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.mockito.Mockito.mock

class TopHeadlinesViewModelTestRobot(
    private val mockRepository: NewsService = mock(NewsService::class.java),
    private val testDispatcherProvider: DispatcherProvider = DispatcherProvider(
        IO = Dispatchers.Unconfined,
        Main = Dispatchers.Unconfined
    )
) {
    private lateinit var viewModel: TopHeadlinesViewModel

    fun mockArticleListResponse(response: List<TopHeadlines.Article>) = apply {
        runBlocking {
            whenever(mockRepository.getTopHeadlines()).thenReturn(response)
        }
    }

    fun mockArticleListError(error: Throwable = IllegalArgumentException()) = apply {
        runBlocking {
            whenever(mockRepository.getTopHeadlines()).thenThrow(error)
        }
    }

    fun buildViewModel() = apply {
        viewModel = TopHeadlinesViewModel(
            mockRepository,
            testDispatcherProvider
        )
    }

    fun assertShowLoading(showLoading: Boolean) = apply{
        assertEquals(showLoading, viewModel.showLoading.testObserver().observedValue)
    }

    fun assertShowData(showData: Boolean) = apply {
        assertEquals(showData, viewModel.showData.testObserver().observedValue)
    }

    fun assertShowError(showError: Boolean) = apply {
        assertEquals(showError, viewModel.showError.testObserver().observedValue)
    }

    fun assertArticleList(githubRepoList: ArrayList<TopHeadlines.Article>) = apply {
        assertEquals(githubRepoList, viewModel.articleList.testObserver().observedValue)
    }
}