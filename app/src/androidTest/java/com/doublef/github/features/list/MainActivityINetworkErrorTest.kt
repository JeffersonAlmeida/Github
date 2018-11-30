package com.doublef.github.features.list

import android.content.Context
import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.doublef.github.R
import com.doublef.github.model.RepositoryItem
import com.doublef.github.util.AppDispatcher
import com.doublef.github.util.JsonHelper
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityINetworkErrorTest {

    @JvmField @get:Rule
    val mActivityTestRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, true, false)

    private lateinit var webServer: MockWebServer
    private lateinit var appDispatcher: AppDispatcher
    private lateinit var mockedData: MutableList<RepositoryItem>
    private lateinit var context: Context

    @Before
    fun before(){

        webServer = MockWebServer()
        mockedData = JsonHelper().getMockedData()
        appDispatcher = AppDispatcher()
        context = InstrumentationRegistry.getInstrumentation().targetContext

        val jsonResponse = Gson().toJson(mockedData)
        appDispatcher.mockResponse = MockResponse()
            .setResponseCode(500)
            .setBody(jsonResponse)

        webServer.setDispatcher(appDispatcher)
        webServer.start(8888)
    }

    @After
    fun after(){
        webServer.shutdown()
    }

    @Test
    fun testNetworkError() {
        mActivityTestRule.launchActivity(Intent())

        val errorTitle = context.resources.getString(R.string.error_title)
        val errorMsg = context.resources.getString(R.string.error_msg)

        Espresso
            .onView(withText(errorTitle))
            .check(matches(isDisplayed()))

        Espresso
            .onView(withText(errorMsg))
            .check(matches(isDisplayed()))

        val cancel = context.resources.getString(android.R.string.cancel)
        Espresso
            .onView(withText(cancel))
            .check(matches(isDisplayed()))
            .perform(ViewActions.click())
    }
}


