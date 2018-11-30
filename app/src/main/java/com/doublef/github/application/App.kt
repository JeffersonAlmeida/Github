package com.doublef.github.application

import android.app.Application
import com.doublef.github.features.list.MainViewModel
import com.doublef.github.features.list.Repository
import com.doublef.github.network.Network
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.startKoin

open class App : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin(listOf(appModule))
    }

    private val appModule = module {
        single { MainViewModel(get()) }
        single { Repository(get()) }
        single { Network.Builder().build() }
    }

}
