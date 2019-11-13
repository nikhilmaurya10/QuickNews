package com.senra.assing.quicknews

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers
import com.senra.assing.quicknews.ui.articleView.ArticleViewActivity

class ArticleListRobot {

    fun assertDataDisplayed() = apply {
        Espresso.onView(recyclerViewMatcher)
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun waitForCondition(idlingResource: IdlingResource?) = apply {
        IdlingRegistry.getInstance().register(idlingResource)
    }

    fun assertArticleAtPosition(position: Int, repoName: String) = apply {
        val itemMatcher = RecyclerViewMatcher(recyclerViewId)
            .atPositionOnView(
                position,
                title
            )
        Espresso.onView(itemMatcher)
            .check(ViewAssertions.matches(ViewMatchers.withText(repoName)))
    }

    fun clickItem(position: Int) = apply {
        val itemMatcher = RecyclerViewMatcher(recyclerViewId).atPosition(position)
        Espresso.onView(itemMatcher).perform(ViewActions.click())
        Intents.init()
        intended(hasComponent(ArticleViewActivity::class.java.name))
        Intents.release()
    }


    companion object {
        private const val recyclerViewId = R.id.article_list
        private const val title = R.id.title

        private val recyclerViewMatcher = ViewMatchers.withId(R.id.article_list)
        private val errorViewMatcher = ViewMatchers.withId(R.id.errorView)
    }
}