package com.doublef.github.runner

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.doublef.github.app.AppTest

public class TestAppJUnitRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, AppTest::class.java.name, context)
    }
}