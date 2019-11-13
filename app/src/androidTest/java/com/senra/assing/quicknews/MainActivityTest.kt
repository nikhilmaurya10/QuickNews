package com.senra.assing.quicknews

import android.view.View
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.senra.assing.quicknews.ui.MainActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @JvmField
    @Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)
    private val mockWebServer = MockWebServer()

    private var progressBarGoneIdlingResource: ViewVisibilityIdlingResource? = null

    @Before
    fun setup() {
        mockWebServer.start(8080)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
        IdlingRegistry.getInstance().unregister(progressBarGoneIdlingResource)
    }


    @Test
    fun displayArticleList() {
        mockWebServer.dispatcher = ResultDispatcher()
        activityTestRule.launchActivity(null)
        progressBarGoneIdlingResource =
            ViewVisibilityIdlingResource(
                activityTestRule.activity.findViewById(R.id.progress_bar),
                View.GONE
            )

        ArticleListRobot()
            .waitForCondition(progressBarGoneIdlingResource)
            .assertDataDisplayed()
            .assertArticleAtPosition(0, "This Arctic blast is shattering hundreds of records and causing deadly road conditions. And it's not over - CNN")
            .assertArticleAtPosition(1, "Ohio State star DE Chase Young suspended one additional game, will return vs. Penn State - CBSSports.com")
            .assertArticleAtPosition(2, "Camila Cabello Reveals Tour & Release Dates for New Album, 'Romance' - Showbiz Cheat Sheet")
    }

//    @Test
//    fun clickItem() {
//
//        mockWebServer.dispatcher = ResultDispatcher()
//        activityTestRule.launchActivity(null)
//        progressBarGoneIdlingResource =
//            ViewVisibilityIdlingResource(
//                activityTestRule.activity.findViewById(R.id.progress_bar),
//                View.GONE
//            )
//
//        ArticleListRobot()
//            .waitForCondition(progressBarGoneIdlingResource)
//            .assertDataDisplayed()
//            .clickItem(0)
//    }




}