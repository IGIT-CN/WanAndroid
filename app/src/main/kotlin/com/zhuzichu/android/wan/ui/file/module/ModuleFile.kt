package com.zhuzichu.android.wan.ui.file.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.ChildFragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.file.fragment.FragmentFile
import com.zhuzichu.android.wan.ui.file.viewmodel.ViewModelFile
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleFile {

    @ChildFragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentFile(): FragmentFile

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelFile::class)
    abstract fun bindViewModelFile(viewModel: ViewModelFile): ViewModel

}