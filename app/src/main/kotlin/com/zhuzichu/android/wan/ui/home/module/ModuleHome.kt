package com.zhuzichu.android.wan.ui.home.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.home.fragment.FragmentHome
import com.zhuzichu.android.wan.ui.home.viewmodel.ViewModelHome
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleHome {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentHome(): FragmentHome

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelHome::class)
    abstract fun bindViewModelHome(viewModel: ViewModelHome): ViewModel

}