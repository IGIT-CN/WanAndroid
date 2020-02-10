package com.zhuzichu.android.wan.vxposed

import com.lody.virtual.client.core.VirtualCore.AppRequestListener
import com.zhuzichu.android.shared.extension.toast

class WanAppRequestListener : AppRequestListener {
    override fun onRequestInstall(path: String) {
        "Install: $path".toast()
    }

    override fun onRequestUninstall(pkg: String) {
        "Uninstall: $pkg".toast()
    }

}
