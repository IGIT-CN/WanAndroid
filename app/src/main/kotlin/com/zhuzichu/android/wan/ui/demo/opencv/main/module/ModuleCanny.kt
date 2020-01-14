package com.zhuzichu.android.wan.ui.demo.opencv.main.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.demo.opencv.main.fragment.FragmentCanny
import com.zhuzichu.android.wan.ui.demo.opencv.main.viewmodel.ViewModelCanny
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleCanny {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentCanny(): FragmentCanny

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelCanny::class)
    abstract fun bindViewModelCanny(viewModel: ViewModelCanny): ViewModel

}