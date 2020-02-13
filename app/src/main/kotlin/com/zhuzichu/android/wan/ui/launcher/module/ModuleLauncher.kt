package com.zhuzichu.android.wan.ui.launcher.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.ChildFragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.launcher.fragment.FragmentLauncher
import com.zhuzichu.android.wan.ui.launcher.viewmodel.ViewModelLauncher
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleLauncher {

    @ChildFragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentMe(): FragmentLauncher

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelLauncher::class)
    abstract fun bindViewModelLauncher(viewModel: ViewModelLauncher): ViewModel

}