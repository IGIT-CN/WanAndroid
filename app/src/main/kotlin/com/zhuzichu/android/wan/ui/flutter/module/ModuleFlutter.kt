package com.zhuzichu.android.wan.ui.flutter.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.ChildFragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.flutter.fragment.FragmentFlutter
import com.zhuzichu.android.wan.ui.flutter.viewmodel.ViewModelFlutter
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleFlutter {

    @ChildFragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentHome(): FragmentFlutter

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelFlutter::class)
    abstract fun bindViewModelHome(viewModel: ViewModelFlutter): ViewModel

}