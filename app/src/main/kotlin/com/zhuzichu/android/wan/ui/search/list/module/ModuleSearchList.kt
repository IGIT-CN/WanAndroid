package com.zhuzichu.android.wan.ui.search.list.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.search.list.fragment.FragmentSearchList
import com.zhuzichu.android.wan.ui.search.list.viewmodel.ViewModelSearchList
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleSearchList {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentSearchList(): FragmentSearchList

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelSearchList::class)
    abstract fun bindViewModelSearchList(viewModel: ViewModelSearchList): ViewModel

}