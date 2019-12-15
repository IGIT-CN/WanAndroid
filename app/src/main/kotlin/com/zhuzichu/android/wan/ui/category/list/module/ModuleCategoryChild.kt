package com.zhuzichu.android.wan.ui.category.list.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.ChildFragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.category.list.fragment.FragmentCategoryChild
import com.zhuzichu.android.wan.ui.category.list.viewmodel.ViewModelCategoryChild
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleCategoryChild {

    @ChildFragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentCategoryChild(): FragmentCategoryChild

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelCategoryChild::class)
    abstract fun bindViewModelSearchList(viewModel: ViewModelCategoryChild): ViewModel

}