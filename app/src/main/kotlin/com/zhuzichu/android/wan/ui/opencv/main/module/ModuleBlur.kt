package com.zhuzichu.android.wan.ui.opencv.main.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.opencv.main.fragment.FragmentBlur
import com.zhuzichu.android.wan.ui.opencv.main.viewmodel.ViewModelBlur
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleBlur {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentBlur(): FragmentBlur

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelBlur::class)
    abstract fun bindViewModelBlur(viewModel: ViewModelBlur): ViewModel

}