package com.zhuzichu.android.wan.ui.camera.main.moduel

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.camera.main.fragment.FragmentCameraBasic
import com.zhuzichu.android.wan.ui.camera.main.viewmodel.ViewModelCameraBasic
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleCameraBasic {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentCameraBasic(): FragmentCameraBasic

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelCameraBasic::class)
    abstract fun bindViewModelCamera(viewModel: ViewModelCameraBasic): ViewModel

}