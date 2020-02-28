package com.zhuzichu.android.wan.ui.demo.vxposed.applist.viewmodel

import androidx.lifecycle.MutableLiveData
import com.lody.virtual.client.core.InstallStrategy
import com.lody.virtual.client.core.VirtualCore
import com.lody.virtual.helper.utils.DeviceUtil
import com.lody.virtual.remote.InstallResult
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.wan.base.ItemViewModelAnalyticsBase
import com.zhuzichu.android.wan.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.bus.RxBus
import com.zhuzichu.android.shared.ext.*
import com.zhuzichu.android.wan.event.EventClone
import com.zhuzichu.android.wan.repository.entity.EntityApp

class ItemViewModelApp(
    viewModel: ViewModelAnalyticsBase,
    private val entityApp: EntityApp
) : ItemViewModelAnalyticsBase(viewModel) {

    val name = MutableLiveData(entityApp.name)

    val icon = MutableLiveData(entityApp.icon)

    val onClickClone = createCommand {
        val installedAppInfo = VirtualCore.get().getInstalledAppInfo(entityApp.packageName, 0)
        if (installedAppInfo == null) {
            createFlowable<InstallResult> {
                onNext(addVirtualApp())
                onComplete()
            }.bindToSchedulers()
                .bindToException()
                .autoLoading(viewModel)
                .autoDispose(viewModel)
                .subscribe(
                    {
                        if (it.isSuccess) {
                            "克隆成功".toast()
                            RxBus.post(EventClone.OnSuccessEvent())
                        }
                    }, {
                        viewModel.handleThrowable(it)
                    }
                )

        } else {

        }
    }

    private fun addVirtualApp(): InstallResult {
        var flags = InstallStrategy.COMPARE_VERSION or InstallStrategy.SKIP_DEX_OPT
        entityApp.fastOpen = false
        if (DeviceUtil.isMeizuBelowN()) {
            entityApp.fastOpen = true
        }
        if (false != entityApp.fastOpen) {
            flags = flags or InstallStrategy.DEPEND_SYSTEM_IF_EXIST
        }
        if (false != entityApp.disableMultiVersion) {
            flags = flags or InstallStrategy.UPDATE_IF_EXIST
        }
        return VirtualCore.get().installPackage(entityApp.path, flags)
    }

}