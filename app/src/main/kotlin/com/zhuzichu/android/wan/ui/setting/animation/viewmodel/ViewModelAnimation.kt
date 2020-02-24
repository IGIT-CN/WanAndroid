package com.zhuzichu.android.wan.ui.setting.animation.viewmodel

import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.shared.extension.map
import com.zhuzichu.android.shared.storage.GlobalStorage
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.base.ViewModelAnalyticsBase
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import javax.inject.Inject


class ViewModelAnimation @Inject constructor(
) : ViewModelAnalyticsBase() {

    val items = MutableLiveData<List<Any>>()

    val itemBinding = OnItemBindClass<Any>().apply {
        map<ItemViewModelAnimation>(BR.item, R.layout.item_animation)
    }

    fun updateData() {
        items.value = listOf(
            ItemViewModelAnimation(
                this,
                GlobalStorage.ANIMATION_FADE,
                R.string.fade,
                GlobalStorage.animation == GlobalStorage.ANIMATION_FADE
            ),
            ItemViewModelAnimation(
                this,
                GlobalStorage.ANIMATION_SLIDE,
                R.string.slide,
                GlobalStorage.animation == GlobalStorage.ANIMATION_SLIDE
            ),
            ItemViewModelAnimation(
                this,
                GlobalStorage.ANIMATION_NO,
                R.string.noanim,
                GlobalStorage.animation == GlobalStorage.ANIMATION_NO
            )
        )
    }

}