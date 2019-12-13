package com.zhuzichu.android.wan.ui.me.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.ChildFragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.me.fragment.FragmentMe
import com.zhuzichu.android.wan.ui.me.viewmodel.ViewModelMe
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleMe {

    @ChildFragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentMe(): FragmentMe

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelMe::class)
    abstract fun bindViewModelMe(viewModel: ViewModelMe): ViewModel

}