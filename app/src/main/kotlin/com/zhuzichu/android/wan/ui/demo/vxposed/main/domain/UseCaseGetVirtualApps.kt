package com.zhuzichu.android.wan.ui.demo.vxposed.main.domain

import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.extension.bindToSchedulers
import com.zhuzichu.android.shared.global.AppGlobal.context
import com.zhuzichu.android.wan.repository.LocalRepository
import com.zhuzichu.android.wan.ui.demo.vxposed.main.viewmodel.ItemViewModelVirtualApp
import io.reactivex.Flowable
import javax.inject.Inject

class UseCaseGetVirtualApps @Inject constructor(
    private val localRepository: LocalRepository
) : UseCase<BaseViewModel, Flowable<List<ItemViewModelVirtualApp>>>() {

    override fun execute(parameters: BaseViewModel): Flowable<List<ItemViewModelVirtualApp>> {
        return localRepository.getVirtualApps()
            .map {
                it.map { item ->
                    val appInfo = item.getApplicationInfo(0)
                    val packageManager = context.packageManager
                    ItemViewModelVirtualApp(
                        parameters,
                        item,
                        appInfo.loadIcon(packageManager),
                        appInfo.loadLabel(packageManager).toString()
                    )
                }
            }
            .bindToSchedulers()
    }
}