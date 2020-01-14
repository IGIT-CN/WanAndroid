package com.zhuzichu.android.wan.ui.demo.opencv.main.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.demo.opencv.main.fragment.FragmentIdcard
import com.zhuzichu.android.wan.ui.demo.opencv.main.viewmodel.ViewModelIdcard
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleIdcard {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentIdcard(): FragmentIdcard

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelIdcard::class)
    abstract fun bindViewModelIdcard(viewModel: ViewModelIdcard): ViewModel

}