package com.zhuzichu.android.wan.ui.search.main.viewmodel

import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.wan.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.ext.createCommand
import com.zhuzichu.android.shared.ext.itemDiffOf
import com.zhuzichu.android.shared.ext.map
import com.zhuzichu.android.shared.ext.toast
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.db.entity.DOKeyword
import com.zhuzichu.android.wan.ui.search.main.domain.UseCaseDropHistoryKey
import com.zhuzichu.android.wan.ui.search.main.domain.UseCaseGetHistoryKey
import com.zhuzichu.android.wan.ui.search.main.domain.UseCaseGetHotKey
import com.zhuzichu.android.wan.ui.search.main.domain.UseCasePlusHistoryKey
import com.zhuzichu.android.wan.ui.search.main.fragment.FragmentSearchDirections
import me.tatarka.bindingcollectionadapter2.collections.AsyncDiffObservableList
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import javax.inject.Inject

class ViewModelSearch @Inject constructor(
    private val useCaseGetHotKey: UseCaseGetHotKey,
    private val useCaseGetHistoryKey: UseCaseGetHistoryKey,
    private val useCasePlusHistoryKey: UseCasePlusHistoryKey,
    private val useCaseDropHistoryKey: UseCaseDropHistoryKey
) : ViewModelAnalyticsBase() {

    val keyword = MutableLiveData<String>()

    private val diff = itemDiffOf<ItemViewModelSearchKeyword> { oldItem, newItem ->
        oldItem.keyword.value == newItem.keyword.value
    }

    private val itemsHot = AsyncDiffObservableList(diff)

    private val itemsHistory = AsyncDiffObservableList(diff)

    val items: ObservableList<Any> = MergeObservableList<Any>()
        .insertItem(
            ItemViewModelSearchKeytype(
                this,
                "历史记录",
                itemsHistory,
                true
            )
        )
        .insertList(itemsHistory)
        .insertItem(
            ItemViewModelSearchKeytype(
                this,
                "热搜记录",
                itemsHot,
                false
            )
        )
        .insertList(itemsHot)

    val itemBinding = OnItemBindClass<Any>().apply {
        map<ItemViewModelSearchKeyword>(BR.item, R.layout.item_search_keyword)
        map<ItemViewModelSearchKeytype>(BR.item, R.layout.item_search_keytype)
    }

    val onSearchCommand = createCommand {
        search(keyword = keyword.value)
    }

    fun search(keyword: String?) {
        if (keyword.isNullOrBlank()) {
            "不能为空".toast()
            return
        }
        plusHistoryKeyword(keyword)
        FragmentSearchDirections.actionFragmentSearchToFragmentSearchList(keyword).apply {
            startFragment(this)
        }
    }

    fun updateHotKey() {
        useCaseGetHotKey.execute(Unit)
            .autoDispose(this)
            .subscribe(
                {
                    itemsHot.update(it.data?.map { item ->
                        ItemViewModelSearchKeyword(
                            this,
                            item.name ?: "",
                            false
                        )
                    })
                }, {
                    handleThrowable(it)
                }
            )
    }

    fun updateHistoryKey() {
        useCaseGetHistoryKey.execute(Unit)
            .autoDispose(this)
            .subscribe(
                {
                    itemsHistory.update(it.map { item ->
                        ItemViewModelSearchKeyword(
                            this,
                            item.keyword,
                            true
                        )
                    })
                },
                {
                    handleThrowable(it)
                }
            )
    }

    private fun plusHistoryKeyword(keyword: String?) {
        if (keyword.isNullOrBlank())
            return
        useCasePlusHistoryKey.execute(DOKeyword(keyword))
    }

    fun dropHistoryKeyword(keyword: String?) {
        if (keyword.isNullOrBlank())
            return
        useCaseDropHistoryKey.execute(listOf(DOKeyword(keyword)))
    }

    fun dropAllHistoryKeyword() {
        useCaseDropHistoryKey.execute(
            itemsHistory.map {
                DOKeyword((it as ItemViewModelSearchKeyword).keyword.value.toString())
            }
        )
    }
}