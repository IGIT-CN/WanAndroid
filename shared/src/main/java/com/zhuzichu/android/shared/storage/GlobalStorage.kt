package com.zhuzichu.android.shared.storage

import androidx.appcompat.app.AppCompatDelegate
import com.tencent.mmkv.MMKV

object GlobalStorage {

    private const val PREFS_NAME = "global"

    const val ANIMATION_FADE = 0
    const val ANIMATION_SLIDE = 1
    const val ANIMATION_NO = 2

    private val prefs: Lazy<MMKV> = lazy {
        MMKV.mmkvWithID(PREFS_NAME)
    }

    var username by StringPreference(prefs, null)
    var nickname by StringPreference(prefs, null)
    var cookies by StringPreference(prefs, null)
    var locale by StringPreference(prefs, "zh")
    var animation by IntPreference(prefs, defaultValue = ANIMATION_NO)
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