package com.zhuzichu.android.wan.ui.category.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.ChildFragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.category.fragment.FragmentCategory
import com.zhuzichu.android.wan.ui.category.viewmodel.ViewModelCategory
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleCategory {

    @ChildFragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentCategory(): FragmentCategory

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelCategory::class)
    abstract fun bindViewModelCategory(viewModel: ViewModelCategory): ViewModel

}