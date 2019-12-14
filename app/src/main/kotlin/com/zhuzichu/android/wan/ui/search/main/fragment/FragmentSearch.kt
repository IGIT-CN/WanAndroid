package com.zhuzichu.android.wan.ui.search.main.fragment

import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.zhuzichu.android.libs.internal.MainHandler
import com.zhuzichu.android.libs.tool.showKeyboard
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentSearchBinding
import com.zhuzichu.android.wan.ui.search.main.viewmodel.ViewModelSearch
import kotlinx.android.synthetic.main.fragment_search.*

class FragmentSearch : FragmentAnalyticsBase<FragmentSearchBinding, ViewModelSearch>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_search

    override fun initView() {
        recycler.layoutManager = FlexboxLayoutManager(context).apply {
            justifyContent = JustifyContent.FLEX_START
            flexDirection = FlexDirection.ROW
            flexWrap = FlexWrap.WRAP
        }
        MainHandler.postDelayed(350) {
            showKeyboard(requireContext(), search)
        }
    }

    override fun initFirstData() {
        viewModel.updateHotKey()
        viewModel.updateHistoryKey()
    }

}