package com.zhuzichu.android.wan.manager

class NativeManager {

    init {
        System.loadLibrary("native-lib")
    }

    external fun getStringFromJNI(): String

}