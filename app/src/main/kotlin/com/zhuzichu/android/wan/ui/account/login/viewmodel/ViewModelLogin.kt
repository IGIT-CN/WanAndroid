package com.zhuzichu.android.wan.ui.account.login.viewmodel

import androidx.lifecycle.MutableLiveData
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.autoLoading
import com.zhuzichu.android.shared.storage.GlobalStorage
import com.zhuzichu.android.wan.ActivityMain
import com.zhuzichu.android.wan.ui.account.login.domain.UseCaseLogin
import com.zhuzichu.android.wan.ui.account.login.entity.ParamterLogin
import java.lang.StringBuilder
import javax.inject.Inject

class ViewModelLogin @Inject constructor(
    private val globalStorage: GlobalStorage,
    private val useCaseLogin: UseCaseLogin
) : ViewModelAnalyticsBase() {

    val username = MutableLiveData("zhuzichu520@gmail.com")
    val password = MutableLiveData("qaioasd520")

    val onClickLogin = BindingCommand<Any>({
        val username = username.value ?: ""
        val password = password.value ?: ""
        useCaseLogin.execute(ParamterLogin(username, password))
            .autoLoading(this)
            .autoDispose(this)
            .subscribe({
                val cookies = StringBuilder()
                it.headers().values("Set-Cookie").run {
                    if (isNotEmpty()) {
                        forEach { cookie ->
                            cookies.append(cookie).append(";")
                        }
                        cookies.replace(cookies.length, cookies.length + 1, "")
                    }
                    globalStorage.login(cookies.toString())
                    startActivity(ActivityMain::class.java, isPop = true)
                }
            }, {
                handleThrowable(it)
            })
    })

}