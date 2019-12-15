package com.zhuzichu.android.wan.ui.category.list.fragment

import androidx.core.os.bundleOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.zhuzichu.android.libs.tool.doNotEmpty
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.shared.extension.toast
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentCategoryListBinding
import com.zhuzichu.android.wan.ui.category.list.viewmodel.ViewModelCategoryList
import kotlinx.android.synthetic.main.fragment_category_list.*

class FragmentCategoryList :
    FragmentAnalyticsBase<FragmentCategoryListBinding, ViewModelCategoryList>() {

    private val args: FragmentCategoryListArgs by navArgs()

    private val fragments by lazy {
        args.beanNode.children?.map {
            FragmentCategoryChild().apply {
                arguments = bundleOf(::beanNode.name to it)
            }
        }
    }

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_category_list

    override fun initArgs() {
        viewModel.beanNode = args.beanNode
    }

    override fun initView() {
        viewModel.title.value = args.beanNode.name
    }

    override fun initFirstData() {
        viewModel.updateStart(args.index)
    }

    override fun initViewObservable() {
        viewModel.currentIndex.observe(viewLifecycleOwner, Observer { position ->
            recycler_satrt.post {
                doNotEmpty(fragments) {
                    val transient = childFragmentManager.beginTransaction()
                    viewModel.lastIndex?.let {
                        get(it).apply {
                            transient.hide(this)
                        }
                    }
                    get(position).apply {
                        if (isAdded) {
                            transient.show(this)
                        } else {
                            transient.add(R.id.layout_end, this)
                        }
                    }
                    transient.commit()
                    recycler_satrt.smoothScrollToPosition(position)
                }
            }
        })
    }

}