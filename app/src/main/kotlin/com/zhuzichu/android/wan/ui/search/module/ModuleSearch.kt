package com.zhuzichu.android.wan.ui.search.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.me.fragment.FragmentMe
import com.zhuzichu.android.wan.ui.search.fragment.FragmentSearch
import com.zhuzichu.android.wan.ui.search.viewmodel.ViewModelSearch
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleSearch {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentSearch(): FragmentSearch

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelSearch::class)
    abstract fun bindViewModelSearch(viewModel: ViewModelSearch): ViewModel

}