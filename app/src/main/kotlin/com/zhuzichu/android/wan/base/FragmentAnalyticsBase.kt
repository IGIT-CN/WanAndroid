package com.zhuzichu.android.wan.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.umeng.analytics.MobclickAgent
import com.zhuzichu.android.mvvm.base.BaseFragment
import com.zhuzichu.android.shared.ext.className
import com.zhuzichu.android.shared.ext.logi
import com.zhuzichu.android.shared.ext.toast
import com.zhuzichu.android.shared.global.AppGlobal
import com.zhuzichu.android.shared.http.exception.ResponseThrowable
import io.flutter.embedding.android.FlutterActivity
import com.zhuzichu.android.libs.tool.startActivity as startActivity2

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
        viewModel.event.handleThrowableEvent.observe(viewLifecycleOwner, Observer {
            //todo 添加设计模式去掉if else
            val throwable = it.throwable
            "异常错误统一处理:".logi(className(), throwable)
            if (throwable is ResponseThrowable) {
                when (throwable.code) {
                    -1001 -> {
                        startActivity2(activityCtx, AppGlobal.loginClazz) {
                            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        }
                    }
                    else -> {
                    }
                }
                if (it.isToast != false)
                    throwable.message.toast()
                it.closure?.invoke(throwable)
            } else {
                "未知错误".toast()
            }
        })

        viewModel.event.startFlutterActivityEvent.observe(viewLifecycleOwner, Observer {
            val intent = FlutterActivity.withNewEngine().initialRoute(it.route.toString())
                .build(requireContext())
            startActivity(intent)
        })
    }
}