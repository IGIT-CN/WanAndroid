package com.zhuzichu.android.wan.ui.demo.jni.main.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.ext.bindToException
import com.zhuzichu.android.shared.ext.bindToSchedulers
import com.zhuzichu.android.shared.ext.createFlowable
import com.zhuzichu.android.wan.manager.JniDemoManager
import com.zhuzichu.android.wan.ui.demo.jni.main.entity.BeanStudent
import io.reactivex.Flowable
import javax.inject.Inject


class UseCaseGetStudent @Inject constructor(
    private val jniDemoManager: JniDemoManager
) : UseCase<Unit, Flowable<BeanStudent>>() {

    override fun execute(parameters: Unit): Flowable<BeanStudent> =
        createFlowable<BeanStudent> {
            Thread.sleep(250)
            onNext(jniDemoManager.getStudent())
            onComplete()
        }.bindToSchedulers()
            .bindToException()
}