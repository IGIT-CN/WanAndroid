package com.zhuzichu.android.wan.ui.demo.vxposed.applist.domain

import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.extension.bindToException
import com.zhuzichu.android.shared.extension.bindToSchedulers
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
                it.map {item->
                    ItemViewModelApp(parameters, item)
                }
            }
            .bindToSchedulers()
    }
}