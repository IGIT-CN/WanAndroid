package com.zhuzichu.android.wan.ui.file.fragment

import android.Manifest
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.tbruyelle.rxpermissions2.RxPermissions
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.shared.extension.toast
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.base.FragmentAnalyticsBase
import com.zhuzichu.android.wan.databinding.FragmentFileBinding
import com.zhuzichu.android.wan.ui.file.viewmodel.ViewModelFile
import kotlinx.android.synthetic.main.fragment_file.*
import java.io.File


class FragmentFile : FragmentAnalyticsBase<FragmentFileBinding, ViewModelFile>() {

    override fun setLayoutId(): Int = R.layout.fragment_file

    override fun bindVariableId(): Int = BR.viewModel

    private val args: FragmentFileArgs by navArgs()

    override fun initView() {
        val rootFile = File(args.path)
        RxPermissions(this)
            .request(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .autoDispose(viewModel)
            .subscribe { granted ->
                if (granted) {
                    viewModel.loadFileList(rootFile)
                } else {
                    "权限被拒绝".toast()
                }
            }

    }

    override fun initViewObservable() {
        viewModel.onAddFileNavEvent.observe(viewLifecycleOwner, Observer {
            nav.post {
                nav.scrollToPosition(viewModel.navList.value!!.size)
            }
        })
    }
}