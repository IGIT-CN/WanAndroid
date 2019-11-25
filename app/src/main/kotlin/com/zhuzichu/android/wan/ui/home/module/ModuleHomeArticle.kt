package com.zhuzichu.android.wan.ui.home.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.ChildFragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.home.fragment.FragmentHomeArticle
import com.zhuzichu.android.wan.ui.home.viewmodel.ViewModelHomeArticle
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleHomeArticle {

    @ChildFragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentHomeArticle(): FragmentHomeArticle

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelHomeArticle::class)
    abstract fun bindViewModelHomeArticle(viewModel: ViewModelHomeArticle): ViewModel

}