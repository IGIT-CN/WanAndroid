package com.zhuzichu.android.wan.ui.demo.vxposed.loading

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.text.TextUtils
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.lody.virtual.client.core.VirtualCore
import com.lody.virtual.client.env.Constants
import com.lody.virtual.client.ipc.VActivityManager
import com.lody.virtual.client.ipc.VPackageManager
import com.lody.virtual.server.pm.parser.VPackage
import com.zhuzichu.android.libs.internal.MainHandler
import com.zhuzichu.android.shared.base.ActivityAnalyticsBase
import com.zhuzichu.android.shared.extension.className
import com.zhuzichu.android.shared.extension.logi
import com.zhuzichu.android.shared.extension.toast
import com.zhuzichu.android.wan.R
import jonathanfinerty.once.Once
import java.util.*

class ActivityVirtualLoading : ActivityAnalyticsBase() {

    override fun setNavGraph(): Int = R.navigation.navigation_virtual_loading

    private var start: Long = 0

    private var name: String? = null

    private var icon: Bitmap? = null

    private var pkg: String? = null

    private var intentToLaunch: Intent? = null
    private var userToLaunch = 0

    companion object {
        private const val REQUEST_PERMISSION_CODE = 100
        private const val KEY_NAME = "name"
        private const val KEY_ICON = "icon"

        fun launch(
            context: Context,
            packageName: String?,
            name: String?,
            icon: Bitmap?,
            userId: Int
        ): Boolean {
            val intent = VirtualCore.get().getLaunchIntent(packageName, userId)
            return if (intent != null) {
                val loadingPageIntent = Intent(context, ActivityVirtualLoading::class.java)
                loadingPageIntent.putExtra(Constants.PASS_PKG_NAME_ARGUMENT, packageName)
                loadingPageIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                loadingPageIntent.putExtra(Constants.PASS_KEY_INTENT, intent)
                loadingPageIntent.putExtra(Constants.PASS_KEY_USER, userId)
                loadingPageIntent.putExtra(KEY_NAME, name)
                loadingPageIntent.putExtra(KEY_ICON, icon)
                context.startActivity(loadingPageIntent)
                true
            } else {
                false
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        start = SystemClock.elapsedRealtime()
        pkg = intent.getStringExtra(Constants.PASS_PKG_NAME_ARGUMENT)
        name = intent.getStringExtra(KEY_NAME)
        icon = intent.getParcelableExtra(KEY_ICON)
        val userId = intent.getIntExtra(Constants.PASS_KEY_USER, -1)
        val passIntent: Intent? = intent.getParcelableExtra(Constants.PASS_KEY_INTENT)
        if (passIntent == null) {
            finish()
            return
        }
        try {
            // 如果已经在运行了，那么直接拉起，不做任何检测。
            var uiRunning = false
            val am = getSystemService(ACTIVITY_SERVICE) as ActivityManager
            val runningAppProcesses = am.runningAppProcesses
            for (runningAppProcess in runningAppProcesses) {
                val appProcessName = VActivityManager.get().getAppProcessName(runningAppProcess.pid)
                if (TextUtils.equals(appProcessName, pkg)) {
                    uiRunning = true
                    break
                }
            }
            if (uiRunning) {
                launchActivity(passIntent, userId)
                return
            }
        } catch (t: Throwable) {
            "启动虚拟App失败".logi(className(), t)
        }
        checkAndLaunch(passIntent, userId)
    }


    private fun checkAndLaunch(intent: Intent, userId: Int) {
        val apiLevel = Build.VERSION_CODES.M
        if (Build.VERSION.SDK_INT < apiLevel) { // the device is below Android M, the permissions are granted when install, start directly
            launchActivityWithDelay(intent, userId)
            return
        }

        try {
            val applicationInfo = VPackageManager.get().getApplicationInfo(packageName, 0, 0)
            val targetSdkVersion = applicationInfo.targetSdkVersion
            if (targetSdkVersion >= apiLevel) {
                launchActivityWithDelay(intent, userId)
            } else {
                intentToLaunch = intent
                userToLaunch = userId

                val packageInfo = VPackageManager.get()
                    .getPackageInfo(packageName, PackageManager.GET_PERMISSIONS, 0)
                val requestedPermissions = packageInfo.requestedPermissions
                val dangerousPermissions: HashSet<String> = HashSet()
                requestedPermissions.forEach {
                    if (VPackage.PermissionComponent.DANGEROUS_PERMISSION.contains(it)) {
                        if (ContextCompat.checkSelfPermission(this, it)
                            != PackageManager.PERMISSION_GRANTED
                        ) {
                            dangerousPermissions.add(it)
                        }
                    }
                }
                if (dangerousPermissions.isEmpty()) {
                    launchActivityWithDelay(intent, userId)
                } else {
                    val alertDialog = AlertDialog.Builder(this).setTitle("重要提示：")
                        .setMessage(name.plus(":不支持动态申请权限, 您必须提前赋予它需要的所有必要权限, 请在它接下来的权限请求中全部选择允许，否则他可能无法正常运行。"))
                        .setPositiveButton("我知道了") { _, _ ->
                            val permissionToRequest =
                                dangerousPermissions.toTypedArray()
                            try {
                                ActivityCompat.requestPermissions(
                                    this,
                                    permissionToRequest,
                                    REQUEST_PERMISSION_CODE
                                )
                            } catch (ignored: Throwable) {
                            }
                        }.create()
                    try {
                        alertDialog.show()
                    } catch (t: Throwable) {
                        finish()
                        "打开应用失败 :".plus(name).toast()
                    }
                }
            }
        } catch (t: Throwable) {
            launchActivityWithDelay(intent, userId)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION_CODE) {
            var allGranted = true
            for (grantResult in grantResults) {
                if (grantResult == PackageManager.PERMISSION_DENIED) {
                    allGranted = false
                    break
                }
            }

            if (allGranted) {
                intentToLaunch?.let {
                    launchActivityWithDelay(it, userToLaunch)
                } ?: let {
                    "打开应用失败 :".plus(name).toast()
                    finish()
                }
            } else {
                val tag = "permission_tips_" + pkg?.replace("\\.", "_")
                if (!Once.beenDone(tag)) {
                    val alertDialog =
                        AlertDialog.Builder(this).setTitle(android.R.string.dialog_alert_title)
                            .setMessage(name.plus("不支持动态申请权限，如果它工作不正常，请去您设备的系统权限管理中赋予 VirtualXposed 必要权限"))
                            .setPositiveButton("我知道了") { _, _ ->
                                intentToLaunch?.let {
                                    finish()
                                    Once.markDone(tag)
                                    launchActivityWithDelay(it, userToLaunch)
                                } ?: let {
                                    "打开应用失败 :".plus(name).toast()
                                    finish()
                                }
                            }.create()
                    try {
                        alertDialog.show()
                    } catch (t: Throwable) {
                        finish()
                        "打开应用失败 :".plus(name).toast()
                    }
                } else {
                    intentToLaunch?.let {
                        launchActivityWithDelay(it, userToLaunch)
                        finish()
                    } ?: let {
                        finish()
                        "打开应用失败 :".plus(name).toast()
                    }
                }
            }
        }

    }

    private fun launchActivityWithDelay(intent: Intent, userId: Int) {
        val maxWait = 1000
        val delta: Long = SystemClock.elapsedRealtime() - start
        val waitTime = maxWait - delta
        if (waitTime <= 0) {
            launchActivity(intent, userId)
        } else {
            MainHandler.postDelayed(waitTime) {
                launchActivity(intent, userId)
            }
        }
    }

    private fun launchActivity(intent: Intent, userId: Int) {
        try {
            VActivityManager.get().startActivity(intent, userId)
        } catch (e: Throwable) {
            "打开应用失败".toast()
        }finally {
            finish()
        }
    }
}