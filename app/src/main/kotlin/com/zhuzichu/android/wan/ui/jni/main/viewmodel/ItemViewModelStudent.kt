package com.zhuzichu.android.wan.ui.jni.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.createCommand
import com.zhuzichu.android.shared.extension.toast
import com.zhuzichu.android.wan.ui.jni.main.entity.BeanStudent

class ItemViewModelStudent(
    viewModel: BaseViewModel,
    val bean: BeanStudent
) : ItemViewModelAnalyticsBase(viewModel) {

    val name = MutableLiveData("我的名字叫".plus(bean.name))

    val age = MutableLiveData("今年".plus(bean.age).plus("岁了!"))

    val onClickItem = createCommand {
        name.value.toString().toast()
    }

}