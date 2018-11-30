package com.doublef.github.features.list

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.doublef.github.R
import com.doublef.github.util.AppDispatcher
import com.doublef.github.util.JsonHelper
import com.doublef.github.util.RecyclerViewMatcher
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityItemDetailsTest {

    @JvmField @get:Rule
    val mActivityTestRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, true, false)

    private lateinit var webServer: MockWebServer
    private var appDispatcher = AppDispatcher()
    private val mockedData = JsonHelper().getMockedData()

    @Before
    fun before(){
        webServer = MockWebServer()
        val jsonResponse = Gson().toJson(mockedData)
        appDispatcher.mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(jsonResponse)

        webServer.setDispatcher(appDispatcher)
        webServer.start(8888)
    }

    @After
    fun after(){
        webServer.shutdown()
    }

    @Test
    fun testDetailsScreenContent() {
        mActivityTestRule.launchActivity(Intent())

        Espresso
            .onView(RecyclerViewMatcher(R.id.recyclerView)
            .atPosition(0))
            .perform(ViewActions.click())

        Espresso
            .onView(withId(R.id.name))
            .check(matches((withText(mockedData[0].name))))

        Espresso
            .onView(withId(R.id.description))
            .check(matches((withText(mockedData[0].description))))
    }
}


