package com.zhuzichu.android.wan.ui.jni.main.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.extension.bindToException
import com.zhuzichu.android.shared.extension.bindToSchedulers
import com.zhuzichu.android.wan.manager.JniDemoManager
import com.zhuzichu.android.wan.ui.jni.main.entity.BeanStudent
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import javax.inject.Inject


class UseCaseGetStudent @Inject constructor(
    private val jniDemoManager: JniDemoManager
) : UseCase<Unit, Flowable<BeanStudent>>() {

    override fun execute(parameters: Unit): Flowable<BeanStudent> =
        Flowable.create<BeanStudent>(
            {
                Thread.sleep(250)
                it.onNext(jniDemoManager.getStudent())
                it.onComplete()
            }, BackpressureStrategy.BUFFER
        ).bindToSchedulers()
            .bindToException()
}