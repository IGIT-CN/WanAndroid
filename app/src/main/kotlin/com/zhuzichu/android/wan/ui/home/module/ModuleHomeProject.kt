package com.zhuzichu.android.wan.ui.home.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.ChildFragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.home.fragment.FragmentHomeProject
import com.zhuzichu.android.wan.ui.home.viewmodel.ViewModelHomeProject
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleHomeProject {

    @ChildFragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentHomeProject(): FragmentHomeProject

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelHomeProject::class)
    abstract fun bindViewModelHomeProject(viewModel: ViewModelHomeProject): ViewModel

}