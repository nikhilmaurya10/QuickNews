package com.senra.assing.quicknews.di.component

import com.senra.assing.quicknews.App
import com.senra.assing.quicknews.di.modules.ApplicationModule
import com.senra.assing.quicknews.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(myApplication: App)

    fun inject(myActivity: MainActivity)

}