package com.zhuzichu.android.wan.ui.category.list.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.category.list.fragment.FragmentCategoryList
import com.zhuzichu.android.wan.ui.category.list.viewmodel.ViewModelCategoryList
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleCategoryList {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentCategoryList(): FragmentCategoryList

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelCategoryList::class)
    abstract fun bindViewModelSearchList(viewModel: ViewModelCategoryList): ViewModel

}