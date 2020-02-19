package com.zhuzichu.android.wan.ui.demo.jni.main.fragment

import androidx.lifecycle.Observer
import com.zhuzichu.android.wan.base.FragmentAnalyticsBase
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentJniBinding
import com.zhuzichu.android.wan.ui.demo.jni.main.viewmodel.ViewModelJni
import kotlinx.android.synthetic.main.fragment_jni.*

class FragmentJni : FragmentAnalyticsBase<FragmentJniBinding, ViewModelJni>() {
    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_jni

    override fun initViewObservable() {
        viewModel.onPlusStudentEvent.observe(viewLifecycleOwner, Observer {
            recycler.adapter?.let {
                recycler.postDelayed({
                    recycler.scrollToPosition(it.itemCount - 1)
                }, 400)
            }
        })
    }
}