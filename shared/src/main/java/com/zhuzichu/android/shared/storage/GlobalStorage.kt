package com.zhuzichu.android.shared.storage

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import com.zhuzichu.android.shared.global.AppGlobal.context

class GlobalStorage {
    companion object {
        const val PREFS_NAME = "global-preference"

        const val ANIMATION_FADE = 0
        const val ANIMATION_SLIDE = 1
    }

    private val prefs: Lazy<SharedPreferences> = lazy {
        context.applicationContext.getSharedPreferences(
            PREFS_NAME, Context.MODE_PRIVATE
        )
    }

    var username by StringPreference(prefs, null)
    var nickname by StringPreference(prefs, null)
    var cookies by StringPreference(prefs, null)
    var locale by StringPreference(prefs, "zh")
    var animation by IntPreference(prefs, defaultValue = ANIMATION_FADE)
    var uiMode by IntPreference(prefs, defaultValue = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

    fun login(cookies: String?, username: String?, nickname: String?) {
        this.cookies = cookies
        this.username = username
        this.nickname = nickname
    }

    fun logout() {
        this.cookies = null
        this.username = null
        this.nickname = null
    }
}