package com.senra.assing.quicknews

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.senra.assing.quicknews.MockConstants.LIST_JSON
import com.senra.assing.quicknews.Util.asset
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class ResultDispatcher(
private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
) : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        val responseBody = asset(context, LIST_JSON)
        return MockResponse().setResponseCode(200).setBody(responseBody)
    }
}
