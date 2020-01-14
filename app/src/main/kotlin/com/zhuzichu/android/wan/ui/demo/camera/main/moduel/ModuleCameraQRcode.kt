package com.zhuzichu.android.wan.ui.demo.camera.main.moduel

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.demo.camera.main.fragment.FragmentCameraQRcode
import com.zhuzichu.android.wan.ui.demo.camera.main.viewmodel.ViewModelCameraQRcode
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleCameraQRcode {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentCameraBasic(): FragmentCameraQRcode

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelCameraQRcode::class)
    abstract fun bindViewModelCamera(viewModel: ViewModelCameraQRcode): ViewModel

}