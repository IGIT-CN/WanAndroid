package com.zhuzichu.android.wan.ui.jni.main.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.recyclerview.widget.DiffUtil
import com.zhuzichu.android.mvvm.event.SingleLiveEvent
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.map
import com.zhuzichu.android.shared.extension.toStringByResId
import com.zhuzichu.android.shared.extension.toast
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.manager.JniDemoManager
import com.zhuzichu.android.wan.ui.jni.main.entity.BeanStudent
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import java.util.ArrayList
import javax.inject.Inject

class ViewModelJni @Inject constructor(
    private val jniDemoManager: JniDemoManager
) : ViewModelAnalyticsBase() {

    val onPlusStudentEvent = SingleLiveEvent<Unit>()

    /**
     * 标题
     */
    val title = MutableLiveData(R.string.jni.toStringByResId())

    init {
        jniDemoManager.onInvokeLisenter = {
            title.value = R.string.jni.toStringByResId().plus(this)
        }
    }

    /**
     * jni条目操作类型
     */
    companion object {
        const val TYPE_STATIC = 0
        const val TYPE_DYNAMIC = 1
        const val TYPE_RANDOM = 2
    }

    /***
     * 点击jni条目返回的闭包回调
     */
    private val closure: Int.() -> Unit = {
        when (this) {
            TYPE_STATIC -> {
                jniDemoManager.invokeStaticMethod().toast()
            }
            TYPE_DYNAMIC -> {
                jniDemoManager.invokeDynamicMethod().toast()
            }
            TYPE_RANDOM -> {
                jniDemoManager.getRandNumber().toString().toast()
            }
            else -> {
            }
        }
    }

    /**
     * 学生list
     */
    private val itemsStudent = MutableLiveData<List<ItemViewModelStudent>>().also {
        it.value = listOf()
    }
    /**
     * jni操作条目list
     */
    private val itemsJni = listOf(
        ItemViewModelJni(this, TYPE_STATIC, "静态注册native方法", closure),
        ItemViewModelJni(this, TYPE_DYNAMIC, "动态注册native方法", closure),
        ItemViewModelJni(this, TYPE_RANDOM, "获取随机数 C++调用java方法", closure)
    )

    /**
     * 数据合并
     */
    val items: LiveData<List<Any>> =
        Transformations.map<List<ItemViewModelStudent>, List<Any>>(itemsStudent) { input ->
            val list = ArrayList<Any>()
            list.addAll(itemsJni)
            list.addAll(input)
            list.add(ItemViewModelStudentOperate(this, jniDemoManager))
            list
        }

    /**
     * 初始化多布局
     */
    val itemBinding = OnItemBindClass<Any>().apply {
        map<ItemViewModelJni>(BR.item, R.layout.item_jni)
        map<ItemViewModelStudent>(BR.item, R.layout.item_student)
        map<ItemViewModelStudentOperate>(BR.item, R.layout.item_student_operate)
    }

    /**
     * 添加一个学生
     */
    fun plusSutdent(bean: BeanStudent) {
        itemsStudent.value?.let {
            itemsStudent.value = it.plus(ItemViewModelStudent(this, bean))
            onPlusStudentEvent.call()
        }
    }

    /**
     * 删除一个学生
     */
    fun minusStudent() {
        itemsStudent.value?.let {
            if (it.isNotEmpty()) {
                itemsStudent.value = it.dropLast(1)
            }
        }
    }

    val diff: DiffUtil.ItemCallback<Any> = object : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {

            //判断ItemViewModelJni是不是同条数据
            if (oldItem is ItemViewModelJni && newItem is ItemViewModelJni) {
                if (oldItem.type == newItem.type) {
                    return true
                }
            }

            //判断ItemViewModelStudentOperate是不是同条数据
            if (oldItem is ItemViewModelStudentOperate && newItem is ItemViewModelStudentOperate) {
                return true
            }

            //判断ItemViewModelStudent是不是同条数据
            if (oldItem is ItemViewModelStudent && newItem is ItemViewModelStudent) {
                if (oldItem.bean.name == newItem.bean.name) {
                    return true
                }
            }

            return false
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }
    }
}