package com.zhuzichu.android.wan.ui.demo.vxposed.loading.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.demo.vxposed.loading.fragment.FragmentVirtualLoading
import com.zhuzichu.android.wan.ui.demo.vxposed.loading.viewmodel.ViewModelVirtualLoading
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleVirtualLoading {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentVirtualLoading(): FragmentVirtualLoading

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelVirtualLoading::class)
    abstract fun bindViewModelVirtualLoading(viewModel: ViewModelVirtualLoading): ViewModel

}