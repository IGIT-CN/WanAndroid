package com.zhuzichu.android.wan.ui.demo.vxposed.loading.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import javax.inject.Inject

class ViewModelVirtualLoading @Inject constructor() : ViewModelAnalyticsBase() {

    val icon = MutableLiveData<Bitmap>()

    val name = MutableLiveData<String>()

}