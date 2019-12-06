package com.zhuzichu.android.wan.extension

import androidx.navigation.AnimBuilder
import com.zhuzichu.android.shared.R
import com.zhuzichu.android.shared.extension.logi
import com.zhuzichu.android.shared.storage.GlobalStorage


fun Int?.toAnimationBuild(): AnimBuilder.() -> Unit {
    return when (this) {
        GlobalStorage.ANIMATION_FADE -> ({
            enter = R.anim.default_enter
            exit = R.anim.default_exit
            popEnter = R.anim.default_pop_enter
            popExit = R.anim.default_pop_exit
        })
        GlobalStorage.ANIMATION_SLIDE -> ({
            enter = R.anim.slide_enter
            exit = R.anim.slide_exit
            popEnter = R.anim.slide_pop_enter
            popExit = R.anim.slide_pop_exit
        })
        else -> ({
            enter = R.anim.default_enter
            exit = R.anim.default_exit
            popEnter = R.anim.default_pop_enter
            popExit = R.anim.default_pop_exit
        })
    }
}

fun logInvokeTime(closure: Unit.() -> Unit) {
    val start = System.currentTimeMillis()
    closure.invoke(Unit)
    val end = System.currentTimeMillis()
    "运行时间为:".plus(end-start).logi()
}