package com.zhuzichu.android.wan.ui.demo.jni.main.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.demo.jni.main.fragment.FragmentJni
import com.zhuzichu.android.wan.ui.demo.jni.main.viewmodel.ViewModelJni
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleJni {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentJni(): FragmentJni

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelJni::class)
    abstract fun bindViewModelJni(viewModel: ViewModelJni): ViewModel

}