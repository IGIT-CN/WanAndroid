package com.zhuzichu.android.wan.ui.camera.main.moduel

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.camera.main.fragment.FragmentCamera
import com.zhuzichu.android.wan.ui.camera.main.viewmodel.ViewModelCamera
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleCamera {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentCamera(): FragmentCamera

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelCamera::class)
    abstract fun bindViewModelCamera(viewModel: ViewModelCamera): ViewModel

}