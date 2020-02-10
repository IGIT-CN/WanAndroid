package com.zhuzichu.android.wan.repository

import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.lody.virtual.GmsSupport
import com.lody.virtual.client.core.VirtualCore
import com.zhuzichu.android.shared.extension.createFlowable
import com.zhuzichu.android.shared.global.AppGlobal.context
import com.zhuzichu.android.wan.db.AppDatabase
import com.zhuzichu.android.wan.db.entity.DOKeyword
import com.zhuzichu.android.wan.db.entity.DOUser
import com.zhuzichu.android.wan.repository.entity.EntityApp
import io.reactivex.Flowable
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface LocalRepository {

    fun saveUser(user: DOUser)

    fun saveKeyword(keyword: DOKeyword)

    fun getKeywords(): Flowable<List<DOKeyword>>

    fun deleteKeyword(list: List<DOKeyword>)

    fun getInstalledApps(): Flowable<List<EntityApp>>
}

class LocalRepositoryImpl : LocalRepository {

    private val database = AppDatabase.getInstance()

    private val daoUser by lazy { database.daoUser() }

    private val daoKeyword by lazy { database.daoKeyword() }

    override fun saveUser(user: DOUser) {
        GlobalScope.launch {
            daoUser.insert(user)
        }
    }

    override fun saveKeyword(keyword: DOKeyword) {
        GlobalScope.launch {
            daoKeyword.insert(keyword)
        }
    }

    override fun getKeywords(): Flowable<List<DOKeyword>> {
        return daoKeyword.queryKeywords()
    }

    override fun deleteKeyword(list: List<DOKeyword>) {
        GlobalScope.launch {
            daoKeyword.deleteKeyword(list)
        }
    }

    override fun getInstalledApps(): Flowable<List<EntityApp>> {
        return createFlowable {
            onNext(
                convertPackageInfoToAppData(
                    context.packageManager.getInstalledPackages(PackageManager.GET_META_DATA),
                    true
                )
            )
            onComplete()
        }
    }

    private fun convertPackageInfoToAppData(
        pkgList: List<PackageInfo>,
        fastOpen: Boolean
    ): List<EntityApp> {
        val packageManager = context.packageManager
        val list = mutableListOf<EntityApp>()
        val hostPkg = VirtualCore.get().hostPkg
        for (pkg in pkgList) {
            if (hostPkg == pkg.packageName) {
                continue
            }
            if (VirtualCore.TAICHI_PACKAGE == pkg.packageName) {
                continue
            }
            if (isSystemApplication(pkg)) {
                continue
            }
            val ai = pkg.applicationInfo
            val path =
                (if (ai.publicSourceDir != null) ai.publicSourceDir else ai.sourceDir)
                    ?: continue
            val info = EntityApp()
            info.packageName = pkg.packageName
            info.fastOpen = fastOpen
            info.path = path
            info.icon = null
            info.name = ai.loadLabel(packageManager)
            info.version = pkg.versionName
            val installedAppInfo =
                VirtualCore.get().getInstalledAppInfo(pkg.packageName, 0)
            if (installedAppInfo != null) {
                info.cloneCount = installedAppInfo.installedUsers.size
            }
            if (ai.metaData != null && ai.metaData.containsKey("xposedmodule")) {
                info.disableMultiVersion = true
                info.cloneCount = 0
            }
            list.add(info)
        }
        return list
    }

    private fun isSystemApplication(packageInfo: PackageInfo): Boolean {
        return (packageInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0
                && !GmsSupport.isGmsFamilyPackage(packageInfo.packageName))
    }
}

