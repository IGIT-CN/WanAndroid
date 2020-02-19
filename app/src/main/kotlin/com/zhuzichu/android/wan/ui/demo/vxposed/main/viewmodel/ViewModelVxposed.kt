package com.zhuzichu.android.wan.ui.demo.vxposed.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.mvvm.event.SingleLiveEvent
import com.zhuzichu.android.wan.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.createCommand
import com.zhuzichu.android.shared.extension.map
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.ui.demo.vxposed.main.domain.UseCaseGetVirtualApps
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import javax.inject.Inject

class ViewModelVxposed @Inject constructor(
    private val useCaseGetVirtualApps: UseCaseGetVirtualApps
) : ViewModelAnalyticsBase() {

    val onClickItemEvent = SingleLiveEvent<ItemViewModelVirtualApp>()

    val items = MutableLiveData<List<Any>>()

    val itemBinding = OnItemBindClass<Any>().apply {
        map<ItemViewModelVirtualApp>(BR.item, R.layout.item_app_virtual)
    }

    val onClickCreateApp = createCommand {
        startFragment(R.id.action_fragmentVxposed_to_fragmentAppList)
    }

    fun loadData() {
        useCaseGetVirtualApps.execute(this)
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