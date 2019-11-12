package com.senra.assing.quicknews

import android.app.Application
import com.senra.assing.quicknews.di.component.ApplicationComponent
import com.senra.assing.quicknews.di.component.DaggerApplicationComponent
import com.senra.assing.quicknews.di.modules.ApplicationModule


class App: Application() {

    companion object {
        lateinit var appComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDi()
    }
    private fun initDi() {
        appComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}