package com.senra.assing.quicknews

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.senra.assing.quicknews.model.TopHeadlines
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@SuppressWarnings("UNCHECKED_CAST")
class TopHeadlinesViewModelTest {
    @JvmField
    @Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @JvmField
    @Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val repoListTestRobot = TopHeadlinesViewModelTestRobot()

    @Test
    fun loadData() {
        val testUrl1 = "testurl1"
        val testArticle1 = TopHeadlines.Article(null,null,null,null,null,null,testUrl1,null)
        val testUrl2 = "testurl2"
        val testArticle2 = TopHeadlines.Article(null,null,null,null,null,null,testUrl2,null)
        val testArticles = listOf(testArticle1, testArticle2)

        repoListTestRobot.mockArticleListResponse(testArticles)
            .buildViewModel()
            .assertShowLoading(false)
            .assertShowError(false)
            .assertShowData(true)


    }

    @Test
    fun loadError() {
        repoListTestRobot.mockArticleListError()
            .buildViewModel()
            .assertShowData(false)
            .assertShowLoading(false)
            .assertShowError(true)
    }

    @Test
    fun getData() {
        val testRepoList = arrayListOf(TopHeadlines.Article(null,null,null,null,null,null,"testurl3",null))

        repoListTestRobot.mockArticleListResponse(testRepoList)
            .buildViewModel()
            .assertShowLoading(false)
            .assertShowError(false)
            .assertShowData(true)
            .assertArticleList(testRepoList)
    }
}