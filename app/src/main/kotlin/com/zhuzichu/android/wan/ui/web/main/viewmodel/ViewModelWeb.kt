package com.zhuzichu.android.wan.ui.web.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import javax.inject.Inject

class ViewModelWeb @Inject constructor() : ViewModelAnalyticsBase() {
    val title = MutableLiveData<String>()

}