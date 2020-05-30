package com.home.futuretalentnetworktestdemo2.common.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.home.futuretalentnetworktestdemo2.common.di.other.ViewModelFactory
import com.home.futuretalentnetworktestdemo2.common.di.other.ViewModelKey
import com.home.futuretalentnetworktestdemo2.list.viewmodel.ListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    abstract fun bindListViewModel(listViewModel: ListViewModel): ViewModel
}
