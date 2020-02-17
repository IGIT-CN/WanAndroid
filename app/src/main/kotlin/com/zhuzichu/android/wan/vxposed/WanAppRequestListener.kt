package com.zhuzichu.android.wan.vxposed

import com.lody.virtual.client.core.InstallStrategy
import com.lody.virtual.client.core.VirtualCore
import com.lody.virtual.client.core.VirtualCore.AppRequestListener
import com.zhuzichu.android.shared.extension.toast
import java.io.IOException

class WanAppRequestListener : AppRequestListener {
    override fun onRequestInstall(path: String) {
        "Installing: $path".toast()
        val res =
            VirtualCore.get().installPackage(path, InstallStrategy.UPDATE_IF_EXIST)
        if (res.isSuccess) {
            try {
                VirtualCore.get().preOpt(res.packageName)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            if (res.isUpdate) {
                ("Update: " + res.packageName + " success!").toast()
            } else {
                ("Install: " + res.packageName + " success!").toast()

            }
        } else {
            ("Install failed: " + res.error).toast()
        }
    }

    override fun onRequestUninstall(pkg: String) {
        "Uninstall: $pkg".toast()
    }

}
