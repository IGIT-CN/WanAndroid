package com.zhuzichu.android.wan.ui.home.fragment

import androidx.core.os.bundleOf
import com.zhuzichu.android.shared.base.DefaultIntFragmentPagerAdapter
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.shared.extension.toStringByResId
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentHomeBinding
import com.zhuzichu.android.wan.ui.home.viewmodel.ViewModelHome
import kotlinx.android.synthetic.main.fragment_home.*

class FragmentHome : FragmentAnalyticsBase<FragmentHomeBinding, ViewModelHome>() {
    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_home

    override fun initView() {
        super.initView()

        val titles = listOf(
            R.string.home_article,
            R.string.home_project
        )

        val fragments = listOf<FragmentHomeChild<*, *>>(
            FragmentHomeArticle(),
            FragmentHomeProject()
        )

        titles.forEachIndexed { index, id ->
            fragments[index].apply {
                arguments = bundleOf(::title.name to id.toStringByResId())
            }
        }

        pager.adapter =
            DefaultIntFragmentPagerAdapter(childFragmentManager, fragments, titles.toList())
        tab.setupWithViewPager(pager)
    }
}