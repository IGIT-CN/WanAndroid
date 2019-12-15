package com.zhuzichu.android.wan.ui.main.fragment

import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.zhuzichu.android.shared.base.DefaultIntFragmentPagerAdapter
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.shared.extension.setupWithViewPager
import com.zhuzichu.android.shared.extension.toast
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentMainBinding
import com.zhuzichu.android.wan.ui.category.main.fragment.FragmentCategory
import com.zhuzichu.android.wan.ui.home.fragment.FragmentHome
import com.zhuzichu.android.wan.ui.main.viewmodel.ViewModelMain
import com.zhuzichu.android.wan.ui.me.fragment.FragmentMe
import kotlinx.android.synthetic.main.fragment_main.*

class FragmentMain : FragmentAnalyticsBase<FragmentMainBinding, ViewModelMain>() {

    private val waitTime = 2000L
    private var touchTime: Long = 0


    override fun setLayoutId(): Int = R.layout.fragment_main

    override fun bindVariableId(): Int = BR.viewModel

    override fun initView() {
        val fragments = listOf<Fragment>(
            FragmentHome(),
            FragmentCategory(),
            FragmentMe()
        )

        val titles = listOf(
            R.string.home,
            R.string.category,
            R.string.me
        )

        content.adapter = DefaultIntFragmentPagerAdapter(childFragmentManager, fragments, titles)
        bottom.setupWithViewPager(content)
        initBackListener()
    }

    private fun initBackListener() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            if (System.currentTimeMillis() - touchTime < waitTime) {
                //退出app并清除任务栈
                requireActivity().finishAndRemoveTask()
            } else {
                touchTime = System.currentTimeMillis()
                R.string.press_again_to_exit.toast()
            }
        }
    }

}