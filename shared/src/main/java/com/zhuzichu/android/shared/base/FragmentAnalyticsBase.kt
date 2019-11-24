package com.zhuzichu.android.shared.base

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.umeng.analytics.MobclickAgent
import com.zhuzichu.android.mvvm.base.BaseFragment
import com.zhuzichu.android.shared.global.AppGlobal
import com.zhuzichu.android.shared.http.exception.ResponseThrowable
import  com.zhuzichu.android.libs.tool.startActivity as startActivity2

abstract class FragmentAnalyticsBase<TBinding : ViewDataBinding, TViewModel : ViewModelAnalyticsBase> :
    BaseFragment<TBinding, TViewModel>() {

    override fun onResume() {
        super.onResume()
        MobclickAgent.onPageStart(this::class.java.simpleName)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd(this::class.java.simpleName)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.handleThrowableEvent.observe(this, Observer {
            val throwable = it.throwable
            if (throwable is ResponseThrowable) {
                when (throwable.code) {
                    -1001 -> {
                        startActivity2(activityCtx, AppGlobal.loginClazz) {
                        }
                    }
                    else -> {
                    }
                }

            } else {
                toast("未知错误")
            }
        })
    }

}