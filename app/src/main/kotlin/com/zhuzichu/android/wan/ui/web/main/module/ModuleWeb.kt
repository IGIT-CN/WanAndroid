package com.zhuzichu.android.wan.ui.web.main.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.web.main.fragment.FragmentWeb
import com.zhuzichu.android.wan.ui.web.main.viewmodel.ViewModelWeb
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleWeb {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentWeb(): FragmentWeb

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelWeb::class)
    abstract fun bindViewModelWeb(viewModel: ViewModelWeb): ViewModel

}