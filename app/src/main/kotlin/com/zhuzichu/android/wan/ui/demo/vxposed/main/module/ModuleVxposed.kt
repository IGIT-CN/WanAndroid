package com.zhuzichu.android.wan.ui.demo.vxposed.main.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.demo.vxposed.main.fragment.FragmentVxposed
import com.zhuzichu.android.wan.ui.demo.vxposed.main.viewmodel.ViewModelVxposed
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleVxposed {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentVxposed(): FragmentVxposed

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelVxposed::class)
    abstract fun bindViewModelVxposed(viewModel: ViewModelVxposed): ViewModel

}