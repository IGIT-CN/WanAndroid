package com.zhuzichu.android.wan.ui.demo.opencv.main.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.demo.opencv.main.fragment.FragmentGray
import com.zhuzichu.android.wan.ui.demo.opencv.main.viewmodel.ViewModelGray
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleGray {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentGray(): FragmentGray

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelGray::class)
    abstract fun bindViewModelGray(viewModel: ViewModelGray): ViewModel

}