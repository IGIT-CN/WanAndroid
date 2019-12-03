package com.zhuzichu.android.wan.ui.opencv.main.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.opencv.main.fragment.FragmentOpencv
import com.zhuzichu.android.wan.ui.opencv.main.viewmodel.ViewModelOpencv
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleOpencv {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentOpencv(): FragmentOpencv

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelOpencv::class)
    abstract fun bindViewModelOpencv(viewModel: ViewModelOpencv): ViewModel

}