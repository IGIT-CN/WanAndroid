package com.zhuzichu.android.wan.ui.opencv.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.map
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.manager.NativeManager
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import javax.inject.Inject

class ViewModelOpencv @Inject constructor(
    private val nativeManager: NativeManager
) : ViewModelAnalyticsBase() {

    companion object {
        const val TYPE_GRAY = 0
    }

    val items = MutableLiveData<List<Any>>().apply {
        value = listOf(
            ItemViewModelOpencv(this@ViewModelOpencv, TYPE_GRAY, R.string.gray)
        )
    }

    val itemBinding = OnItemBindClass<Any>().apply {
        map<ItemViewModelOpencv>(BR.item, R.layout.item_opencv)
    }

}