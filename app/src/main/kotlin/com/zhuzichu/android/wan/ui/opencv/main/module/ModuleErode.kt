package com.zhuzichu.android.wan.ui.opencv.main.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.opencv.main.fragment.FragmentErode
import com.zhuzichu.android.wan.ui.opencv.main.viewmodel.ViewModelErode
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleErode {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentErode(): FragmentErode

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelErode::class)
    abstract fun bindViewModelErode(viewModel: ViewModelErode): ViewModel

}