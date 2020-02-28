package com.zhuzichu.android.wan.ui.setting.main.viewmodel


import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.ui.setting.main.viewmodel.ItemViewModelSetting.Companion.ANIMATION
import com.zhuzichu.android.wan.ui.setting.main.viewmodel.ItemViewModelSetting.Companion.LANGUAGES
import com.zhuzichu.android.wan.ui.setting.main.viewmodel.ItemViewModelSetting.Companion.THEME
import com.zhuzichu.android.wan.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.ext.map
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import javax.inject.Inject

class ViewModelSetting @Inject constructor(

) : ViewModelAnalyticsBase() {

    val items = MutableLiveData<List<Any>>().also {
        it.value = listOf(
            ItemViewModelSetting(
                this,
                LANGUAGES,
                R.string.languages
            ),
            ItemViewModelSetting(
                this,
                THEME,
                R.string.theme
            ),
            ItemViewModelSetting(
                this,
                ANIMATION,
                R.string.animation

            ),
            ItemViewModelLogout(this)
        )
    }

    val itemBinding = OnItemBindClass<Any>().apply {
        map<ItemViewModelSetting>(BR.item, R.layout.item_setting)
        map<ItemViewModelLogout>(BR.item, R.layout.item_logout)
    }


}