package com.zhuzichu.android.wan.vxposed

import android.app.Application
import com.lody.virtual.client.core.VirtualCore
import com.lody.virtual.os.VEnvironment
import me.weishu.exposed.LogcatService


open class BaseVirtualInitializer(
    private val application: Application,
    private val virtualCore: VirtualCore
) : VirtualCore.VirtualInitializer() {

    companion object {
        private const val XPOSED_INSTALLER_PACKAGE: String = "de.robv.android.xposed.installer"
    }


    override fun onVirtualProcess() {
        virtualCore.setCrashHandler(BaseCrashHandler())
        virtualCore.componentDelegate = WanComponentDelegate()
        virtualCore.phoneInfoDelegate = WanPhoneInfoDelegate()
        virtualCore.taskDescriptionDelegate = WanTaskDescDelegate()
        // ensure the logcat service alive when every virtual process start.
        LogcatService.start(
            application,
            VEnvironment.getDataUserPackageDirectory(0, XPOSED_INSTALLER_PACKAGE)
        )
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