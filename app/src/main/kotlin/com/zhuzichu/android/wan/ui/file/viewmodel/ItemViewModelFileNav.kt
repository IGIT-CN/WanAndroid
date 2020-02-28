package com.zhuzichu.android.wan.ui.file.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.shared.ext.createCommand
import com.zhuzichu.android.wan.base.ItemViewModelAnalyticsBase
import java.io.File

/**
 * Created by Android Studio.
 * Blog: zhuzichu.com
 * User: zhuzichu
 * Date: 2019-08-14
 * Time: 11:48
 */
class ItemViewModelFileNav(
    viewModel: ViewModelFile,
    val file: File
) : ItemViewModelAnalyticsBase(viewModel) {

    val fileName = MutableLiveData<String>(file.name)

    val onClickItem = createCommand {
        viewModel.navList.value = viewModel.navList.value!!.dropLastWhile { file.path != (it as ItemViewModelFileNav).file.path }
        viewModel.loadFileList(file, false)
    }

}