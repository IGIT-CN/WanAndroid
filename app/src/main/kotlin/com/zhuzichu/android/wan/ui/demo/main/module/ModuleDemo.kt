package com.zhuzichu.android.wan.ui.demo.main.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.demo.main.fragment.FragmentDemo
import com.zhuzichu.android.wan.ui.me.viewmodel.ViewModelDemo
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleDemo {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentDemo(): FragmentDemo

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelDemo::class)
    abstract fun bindViewModelDemo(viewModel: ViewModelDemo): ViewModel

}