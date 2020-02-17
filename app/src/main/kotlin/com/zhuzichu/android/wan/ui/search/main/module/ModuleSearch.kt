package com.zhuzichu.android.wan.ui.search.main.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.search.main.fragment.FragmentSearch
import com.zhuzichu.android.wan.ui.search.main.viewmodel.ViewModelSearch
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