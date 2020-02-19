package com.zhuzichu.android.wan.ui.search.list.fragment

import androidx.navigation.fragment.navArgs
import com.zhuzichu.android.libs.tool.closeKeyboard
import com.zhuzichu.android.wan.base.FragmentAnalyticsBase
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentSearchListBinding
import com.zhuzichu.android.wan.ui.search.list.viewmodel.ViewModelSearchList

class FragmentSearchList : FragmentAnalyticsBase<FragmentSearchListBinding, ViewModelSearchList>() {

    private val args: FragmentSearchListArgs by navArgs()

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_search_list

    override fun initArgs() {
        viewModel.keyword.value = args.keyword
        closeKeyboard(requireContext())
    }

}