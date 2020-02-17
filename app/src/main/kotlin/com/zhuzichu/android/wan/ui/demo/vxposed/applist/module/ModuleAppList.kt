package com.zhuzichu.android.wan.ui.demo.AppList.applist.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.demo.vxposed.applist.fragment.FragmentAppList
import com.zhuzichu.android.wan.ui.demo.vxposed.applist.viewmodel.ViewModelAppList
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleAppList {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentAppList(): FragmentAppList

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelAppList::class)
    abstract fun bindViewModelAppList(viewModel: ViewModelAppList): ViewModel

}