package com.zhuzichu.android.wan.vxposed

import android.app.Activity
import android.app.Application
import android.content.Intent
import com.lody.virtual.client.hook.delegate.ComponentDelegate

class WanComponentDelegate : ComponentDelegate{
    override fun beforeActivityPause(activity: Activity?) {
    }

    override fun beforeActivityCreate(activity: Activity?) {
    }

    override fun beforeApplicationCreate(application: Application?) {
    }

    override fun afterApplicationCreate(application: Application?) {
    }

    override fun afterActivityDestroy(activity: Activity?) {
    }

    override fun beforeActivityResume(activity: Activity?) {
    }

    override fun afterActivityResume(activity: Activity?) {
    }

    override fun afterActivityCreate(activity: Activity?) {
    }

    override fun afterActivityPause(activity: Activity?) {
    }

    override fun onSendBroadcast(intent: Intent?) {
    }

    override fun beforeActivityDestroy(activity: Activity?) {
    }

}