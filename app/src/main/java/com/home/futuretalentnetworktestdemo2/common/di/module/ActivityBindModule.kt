package com.home.futuretalentnetworktestdemo2.common.di.module

import com.home.futuretalentnetworktestdemo2.list.view.ListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindModule {

    @ContributesAndroidInjector
    abstract fun bindListActivity(): ListActivity
}
