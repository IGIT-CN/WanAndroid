package com.zhuzichu.android.shared.extension

import android.content.Context
import android.content.res.Configuration
import android.view.View
import android.widget.Toast
import androidx.core.view.forEachIndexed
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zhuzichu.android.libs.tool.showKeyboard
import com.zhuzichu.android.shared.global.AppGlobal.context
import com.zhuzichu.android.widget.toast.toast

fun BottomNavigationView.setupWithViewPager(viewPager: ViewPager) {
    viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            this@setupWithViewPager.menu.getItem(position).isChecked = true
        }
    })
    this.setOnNavigationItemSelectedListener {
        this@setupWithViewPager.menu.forEachIndexed { index, item ->
            if (it.itemId == item.itemId) {
                viewPager.setCurrentItem(index, false)
            }
        }
        true
    }
}

fun View.showSoftKeyboard() {
    showKeyboard(this.context, this)
}

fun Context.isDark(): Boolean {
    val mode = this.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    return mode == Configuration.UI_MODE_NIGHT_YES
}

fun String.toast(): Toast {
    return toast(context, this)
}

fun Int.toast(): Toast {
    return toast(context, this)
}