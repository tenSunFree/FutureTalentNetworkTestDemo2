package com.home.futuretalentnetworktestdemo2.common

import android.app.Application
import com.home.futuretalentnetworktestdemo2.common.di.component.ApplicationComponent
import com.home.futuretalentnetworktestdemo2.common.di.component.DaggerApplicationComponent
import com.home.futuretalentnetworktestdemo2.common.di.other.ApplicationInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class FTNTDApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    private lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder()
            .application(this)
            .build()
        appComponent.inject(this)
        ApplicationInjector.init(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }
}
