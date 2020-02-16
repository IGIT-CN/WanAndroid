package com.zhuzichu.android.wan.ui.demo.vxposed.loading

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import com.lody.virtual.client.core.VirtualCore
import com.lody.virtual.server.interfaces.IUiCallback
import com.zhuzichu.android.shared.base.ActivityAnalyticsBase
import com.zhuzichu.android.wan.R

class ActivityVirtualLoading : ActivityAnalyticsBase() {

    override fun setNavGraph(): Int = R.navigation.navigation_virtual_loading

    companion object {
        private const val PKG_NAME_ARGUMENT = "MODEL_ARGUMENT"
        private const val KEY_INTENT = "KEY_INTENT"
        private const val KEY_USER = "KEY_USER"

        fun launch(
            context: Context,
            packageName: String?,
            userId: Int
        ) {
            val intent = VirtualCore.get().getLaunchIntent(packageName, userId)
            if (intent != null) {
                val loadingPageIntent =
                    Intent(context, ActivityVirtualLoading::class.java)
                loadingPageIntent.putExtra(PKG_NAME_ARGUMENT, packageName)
                loadingPageIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                loadingPageIntent.putExtra(KEY_INTENT, intent)
                loadingPageIntent.putExtra(KEY_USER, userId)
                context.startActivity(loadingPageIntent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pkg = intent.getStringExtra(PKG_NAME_ARGUMENT)
        val intent: Intent = intent.getParcelableExtra(KEY_INTENT) ?: return
        VirtualCore.get().setUiCallback(intent,object : IUiCallback{
            override fun onOpenFailed(packageName: String?, userId: Int) {

            }

            override fun onAppOpened(packageName: String?, userId: Int) {

            }

            override fun asBinder(): IBinder {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

}