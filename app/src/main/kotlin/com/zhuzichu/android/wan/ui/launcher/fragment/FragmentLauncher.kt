package com.zhuzichu.android.wan.ui.launcher.fragment

import com.lody.virtual.client.core.VirtualCore
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.wan.base.FragmentAnalyticsBase
import com.zhuzichu.android.shared.ext.bindToSchedulers
import com.zhuzichu.android.shared.ext.createFlowable
import com.zhuzichu.android.wan.ActivityMain
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentLauncherBinding
import com.zhuzichu.android.wan.ui.launcher.viewmodel.ViewModelLauncher
import java.lang.Thread.sleep

class FragmentLauncher : FragmentAnalyticsBase<FragmentLauncherBinding, ViewModelLauncher>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_launcher

    override fun initView() {
        super.initView()
        createFlowable<Unit> {
            var time = System.currentTimeMillis()
            doActionInThread()
            time = System.currentTimeMillis() - time
            val delta = 3000L - time
            if (delta > 0) {
                sleep(time)
            }
            onNext(Unit)
            onComplete()
        }.bindToSchedulers()
            .autoDispose(viewModel)
            .subscribe {
                startActivity(ActivityMain::class.java)
                activity?.finish()
            }
    }

    private fun doActionInThread() {
        if (!VirtualCore.get().isEngineLaunched) {
            VirtualCore.get().waitForEngine()
        }
    }
}