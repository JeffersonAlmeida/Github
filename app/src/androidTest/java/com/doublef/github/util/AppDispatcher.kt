package com.doublef.github.util

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class AppDispatcher : Dispatcher() {
    var mockResponse: MockResponse? = null
    @Throws(InterruptedException::class)
    override fun dispatch(request: RecordedRequest): MockResponse? {
        val path = request.path
        if (path.contains("/repositories")) {
            return mockResponse
        }
        return MockResponse().setResponseCode(404)
    }
}