package com.zhuzichu.android.wan.ui.ffmpeg.main.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.ffmpeg.main.fragment.FragmentFFmpeg
import com.zhuzichu.android.wan.ui.ffmpeg.main.viewmodel.ViewModelFFmpeg
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleFFmpeg {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentFFmpeg(): FragmentFFmpeg

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelFFmpeg::class)
    abstract fun bindViewModelFFmpeg(viewModel: ViewModelFFmpeg): ViewModel

}