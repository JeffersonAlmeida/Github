package com.doublef.github.app

import com.doublef.github.features.list.MainActivityINetworkErrorSuccessTest
import com.doublef.github.features.list.MainActivityINetworkErrorTest
import com.doublef.github.features.list.MainActivityItemDetailsTest
import com.doublef.github.features.list.MainActivityItemDisplayedTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainActivityItemDetailsTest::class,
    MainActivityItemDisplayedTest::class,
    MainActivityINetworkErrorTest::class,
    MainActivityINetworkErrorSuccessTest::class)
class SuitTestCases