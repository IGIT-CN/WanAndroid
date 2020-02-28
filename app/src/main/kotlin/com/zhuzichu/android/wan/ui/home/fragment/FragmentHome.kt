package com.zhuzichu.android.wan.ui.home.fragment

import androidx.core.os.bundleOf
import com.zhuzichu.android.shared.base.DefaultStringFragmentPagerAdapter
import com.zhuzichu.android.wan.base.FragmentAnalyticsBase
import com.zhuzichu.android.shared.ext.toStringByResId
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentHomeBinding
import com.zhuzichu.android.wan.ui.home.viewmodel.ViewModelHome
import kotlinx.android.synthetic.main.fragment_home.*

class FragmentHome : FragmentAnalyticsBase<FragmentHomeBinding, ViewModelHome>() {
    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_home

    override fun initLazyView() {

        val titles = listOf(
            R.string.home_article.toStringByResId(activityCtx),
            R.string.home_project.toStringByResId(activityCtx)
        )

        val fragments = listOf<FragmentHomeChild<*, *>>(
            FragmentHomeArticle(),
            FragmentHomeProject()
        )

        titles.forEachIndexed { index, text ->
            fragments[index].apply {
                arguments = bundleOf(::title.name to text)
            }
        }

        pager.adapter =
            DefaultStringFragmentPagerAdapter(childFragmentManager, fragments, titles)
        tab.setupWithViewPager(pager)

    }

}