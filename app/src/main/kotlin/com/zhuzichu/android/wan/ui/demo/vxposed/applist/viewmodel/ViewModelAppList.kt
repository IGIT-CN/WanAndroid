package com.zhuzichu.android.wan.ui.demo.vxposed.applist.viewmodel

import androidx.lifecycle.MutableLiveData
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.wan.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.map
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.ui.demo.vxposed.applist.domain.UseCaseGetApps
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import javax.inject.Inject

class ViewModelAppList @Inject constructor(
    private val useCaseGetApps: UseCaseGetApps
) : ViewModelAnalyticsBase() {

    val items = MutableLiveData<List<Any>>()

    val itemBinding = OnItemBindClass<Any>().apply {
        map<ItemViewModelApp>(BR.item, R.layout.item_app_list)
    }

    fun loadData() {
        useCaseGetApps.execute(this)
            .autoDispose(this)
            .subscribe(
                {
                    items.value = it
                },
                {
                    handleThrowable(it)
                }
            )
    }
}