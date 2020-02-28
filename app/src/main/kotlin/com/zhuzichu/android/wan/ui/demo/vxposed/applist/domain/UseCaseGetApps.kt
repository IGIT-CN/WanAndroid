package com.zhuzichu.android.wan.ui.demo.vxposed.applist.domain

import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.wan.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.ext.bindToSchedulers
import com.zhuzichu.android.wan.repository.LocalRepository
import com.zhuzichu.android.wan.ui.demo.vxposed.applist.viewmodel.ItemViewModelApp
import io.reactivex.Flowable
import javax.inject.Inject

class UseCaseGetApps @Inject constructor(
    private val localRepository: LocalRepository
) : UseCase<BaseViewModel, Flowable<List<ItemViewModelApp>>>() {

    override fun execute(parameters: BaseViewModel): Flowable<List<ItemViewModelApp>> {
        return localRepository.getInstalledApps()
            .map {
                it.map { item ->
                    ItemViewModelApp(parameters as ViewModelAnalyticsBase, item)
                }
            }
            .bindToSchedulers()
    }
}