package com.zhuzichu.android.shared.extension

import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.shared.http.exception.ExceptionManager.handleException
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

fun <T> schedulersTransformer(): FlowableTransformer<T, T> {
    return FlowableTransformer { observable ->
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}

fun <T> exceptionTransformer(): FlowableTransformer<T, T> {
    return FlowableTransformer { observable ->
        observable.onErrorResumeNext(ErrorResponseFunc<T>())
    }
}

private class ErrorResponseFunc<T> : Function<Throwable, Flowable<T>> {
    override fun apply(t: Throwable): Flowable<T> {
        return Flowable.error(handleException(t))
    }
}


fun <T> Flowable<T>.autoLoading(
    viewModel: BaseViewModel,
    execute: (() -> Boolean)? = null
): Flowable<T> {
    val flag = execute?.invoke() ?: true
    return if (flag)
        this.compose<T> {
            it.doOnSubscribe { viewModel.showLoading() }
                .doFinally { viewModel.hideLoading() }
        }
    else
        this.compose<T> {
            it
        }
}

fun <T> Flowable<T>.bindToException(): Flowable<T> =
    this.compose<T> {
        it.onErrorResumeNext(ErrorResponseFunc<T>())
    }

fun <T> Flowable<T>.bindToSchedulers(): Flowable<T> =
    this.compose<T> {
        it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

fun <T> createFlowable(
    mode: BackpressureStrategy = BackpressureStrategy.ERROR,
    closure: FlowableEmitter<T>.() -> Unit
): Flowable<T> {
    return Flowable.create({
        closure.invoke(it)
    }, mode)
}