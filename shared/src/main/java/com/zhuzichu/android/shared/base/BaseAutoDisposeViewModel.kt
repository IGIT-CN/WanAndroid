package com.zhuzichu.android.shared.base

import com.uber.autodispose.lifecycle.CorrespondingEventsFunction
import com.uber.autodispose.lifecycle.LifecycleEndedException
import com.uber.autodispose.lifecycle.LifecycleScopeProvider
import com.zhuzichu.android.mvvm.base.BaseViewModel
import io.reactivex.subjects.BehaviorSubject
import com.zhuzichu.android.shared.base.BaseAutoDisposeViewModel.ViewModelEvent
import com.zhuzichu.android.shared.base.BaseAutoDisposeViewModel.ViewModelEvent.CLEARED
import com.zhuzichu.android.shared.base.BaseAutoDisposeViewModel.ViewModelEvent.CREATED

import io.reactivex.Observable

abstract class BaseAutoDisposeViewModel : BaseViewModel(),
    LifecycleScopeProvider<ViewModelEvent> {

    private val lifecycleEvents = BehaviorSubject.createDefault(CREATED)

    companion object {

        private val CORRESPONDING_EVENTS = CorrespondingEventsFunction<ViewModelEvent> { event ->
            when (event) {
                CREATED -> CLEARED
                else -> throw LifecycleEndedException(
                    "Cannot bind to ViewModel lifecycle after onCleared."
                )
            }
        }
    }

    override fun lifecycle(): Observable<ViewModelEvent> {
        return lifecycleEvents.hide()
    }

    override fun correspondingEvents(): CorrespondingEventsFunction<ViewModelEvent> {
        return CORRESPONDING_EVENTS
    }

    override fun peekLifecycle(): ViewModelEvent? {
        return lifecycleEvents.value
    }

    enum class ViewModelEvent {
        CREATED, CLEARED
    }

    override fun onCleared() {
        lifecycleEvents.onNext(CLEARED)
        super.onCleared()
    }
}