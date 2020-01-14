package com.zhuzichu.android.wan.ui.demo.websocket.main.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.android.mvvm.di.FragmentScoped
import com.zhuzichu.android.mvvm.di.ViewModelKey
import com.zhuzichu.android.wan.ui.demo.websocket.main.fragment.FragmentWebsocket
import com.zhuzichu.android.wan.ui.demo.websocket.main.viewmodel.ViewModelWebsocket
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleWebsocket {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentWebsocket(): FragmentWebsocket

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelWebsocket::class)
    abstract fun bindViewModelWebsocket(viewModel: ViewModelWebsocket): ViewModel

}