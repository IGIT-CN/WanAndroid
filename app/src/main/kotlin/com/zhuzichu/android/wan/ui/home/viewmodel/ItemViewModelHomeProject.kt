package com.zhuzichu.android.wan.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.wan.repository.entity.BeanArticle
import com.zhuzichu.android.wan.ui.home.domain.UseCaseCollect
import com.zhuzichu.android.wan.ui.home.domain.UseCaseUnCollect

class ItemViewModelHomeProject(
    viewModel: ViewModelAnalyticsBase,
    bean: BeanArticle,
    useCaseCollect: UseCaseCollect,
    useCaseUnCollect: UseCaseUnCollect

) : BaseItemViewModelAricle(viewModel, bean, useCaseCollect, useCaseUnCollect){

    val pic = MutableLiveData(bean.envelopePic)

}