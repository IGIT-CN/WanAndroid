package com.zhuzichu.android.wan.ui.file.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.mvvm.event.SingleLiveEvent
import com.zhuzichu.android.shared.extension.itemBindingOf
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.base.ViewModelAnalyticsBase
import com.zhuzichu.android.wan.ui.file.type.*
import java.io.File
import javax.inject.Inject


class ViewModelFile @Inject constructor() : ViewModelAnalyticsBase() {

    val navList = MutableLiveData<List<Any>>(listOf())

    val list = MutableLiveData<List<Any>>(listOf())

    val navItemBind = itemBindingOf<Any>(BR.item, R.layout.item_file_nav)

    val itemBind = itemBindingOf<Any>(BR.item, R.layout.item_file)


    val onAddFileNavEvent = SingleLiveEvent<Boolean>()


    val allDefaultFileType: ArrayList<FileType> by lazy {
        val fileTypes = ArrayList<FileType>()
        fileTypes.add(AudioFileType())
        fileTypes.add(RasterImageFileType())
        fileTypes.add(CompressedFileType())
        fileTypes.add(DataBaseFileType())
        fileTypes.add(ExecutableFileType())
        fileTypes.add(FontFileType())
        fileTypes.add(PageLayoutFileType())
        fileTypes.add(TextFileType())
        fileTypes.add(VideoFileType())
        fileTypes.add(WebFileType())
        fileTypes
    }

    fun loadFileList(fileDir: File, isAddNav: Boolean = true) {
        val data = fileDir.listFiles()?.map { ItemViewModelFile(this, it) }
        list.value = data
        if (isAddNav) {
            navList.value = navList.value!!.plus(ItemViewModelFileNav(this, fileDir))
            onAddFileNavEvent.call()
        }
    }

}