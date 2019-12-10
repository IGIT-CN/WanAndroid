package com.zhuzichu.android.wan.ui.ffmpeg.main.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.ffmpeg.main.fragment.FragmentAVinfo
import com.zhuzichu.android.wan.ui.ffmpeg.main.viewmodel.ViewModelAVinfo
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleAVinfo {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentAVinfo(): FragmentAVinfo

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelAVinfo::class)
    abstract fun bindViewModelAVinfo(viewModel: ViewModelAVinfo): ViewModel

}