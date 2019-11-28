package com.zhuzichu.android.wan.manager

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.wan.repository.entity.BeanLogin

class LoginManager {

    val user = MutableLiveData<BeanLogin>()

    fun isLogin(): Boolean {
        return user.value != null
    }

    fun doLogin(closure: () -> Unit): LoginManager {
        if (isLogin()) {
            closure.invoke()
        }
        return this
    }

    fun doNoLogin(closure: () -> Unit): LoginManager {
        if (!isLogin()) {
            closure.invoke()
        }
        return this
    }

}