package com.home.futuretalentnetworktestdemo2.common.di.module

import com.home.futuretalentnetworktestdemo2.detail.view.activity.DetailActivity
import com.home.futuretalentnetworktestdemo2.list.view.activity.ListActivity
import com.home.futuretalentnetworktestdemo2.pagination.view.activity.PaginationActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindModule {

    @ContributesAndroidInjector
    abstract fun bindListActivity(): ListActivity

    @ContributesAndroidInjector
    abstract fun bindDetailActivity(): DetailActivity

    @ContributesAndroidInjector
    abstract fun bindPaginationActivity(): PaginationActivity
}
