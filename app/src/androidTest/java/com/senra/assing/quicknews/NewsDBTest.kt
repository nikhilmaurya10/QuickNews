package com.senra.assing.quicknews

import androidx.room.Room
import androidx.test.rule.ActivityTestRule
import com.senra.assing.quicknews.data.local.NewsDB
import com.senra.assing.quicknews.data.local.TopHeadlinesDao
import com.senra.assing.quicknews.model.TopHeadlines
import com.senra.assing.quicknews.ui.MainActivity
import kotlinx.coroutines.runBlocking
import org.junit.*

class NewsDBTest {
    private lateinit var database: NewsDB
    private lateinit var dao: TopHeadlinesDao

    @JvmField
    @Rule
    val mainActivity = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        val context = mainActivity.activity
        database = Room.inMemoryDatabaseBuilder(context, NewsDB::class.java)
            .allowMainThreadQueries().build()
        dao = database.topHeadlinesDao()
    }

    @After
    fun tearDown() {
        runBlocking {
            dao.deleteAllArticles()
            database.close()
        }
    }

    @Test
    fun insertReadRepo() {
        runBlocking {
            val testUrl = "testurl"
            val testArticle = TopHeadlines.Article(null,null,null,null,null,null,testUrl,null)
            val testArticles = listOf(testArticle)
            dao.insertTopArtilces(testArticles)

            val result = dao.getArticleByURL(testUrl)

            Assert.assertEquals(testArticles[0].url, result.url)
        }
    }
}