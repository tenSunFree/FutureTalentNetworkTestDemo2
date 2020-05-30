package com.home.futuretalentnetworktestdemo2.common.di.module

import androidx.lifecycle.ViewModelProvider
import com.home.futuretalentnetworktestdemo2.common.di.other.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
