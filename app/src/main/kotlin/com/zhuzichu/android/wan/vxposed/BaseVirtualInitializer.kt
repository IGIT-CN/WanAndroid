package com.zhuzichu.android.wan.vxposed

import com.lody.virtual.client.core.VirtualCore

open class BaseVirtualInitializer(
    private val virtualCore: VirtualCore
) : VirtualCore.VirtualInitializer() {

    override fun onVirtualProcess() {
        virtualCore.setCrashHandler(WanCrashHandler())
        virtualCore.componentDelegate = WanComponentDelegate()
        virtualCore.phoneInfoDelegate = WanPhoneInfoDelegate()
        virtualCore.taskDescriptionDelegate = WanTaskDescDelegate()
    }

    override fun onServerProcess() {
        virtualCore.setAppRequestListener(WanAppRequestListener())
        virtualCore.addVisibleOutsidePackage("com.tencent.mobileqq")
        virtualCore.addVisibleOutsidePackage("com.tencent.mobileqqi")
        virtualCore.addVisibleOutsidePackage("com.tencent.minihd.qq")
        virtualCore.addVisibleOutsidePackage("com.tencent.qqlite")
        virtualCore.addVisibleOutsidePackage("com.facebook.katana")
        virtualCore.addVisibleOutsidePackage("com.whatsapp")
        virtualCore.addVisibleOutsidePackage("com.tencent.mm")
        virtualCore.addVisibleOutsidePackage("com.immomo.momo")
    }

}