package com.zhuzichu.android.wan.vxposed

import android.annotation.TargetApi
import android.app.ActivityManager
import android.os.Build
import com.lody.virtual.client.hook.delegate.TaskDescriptionDelegate
import com.lody.virtual.os.VUserManager

@Suppress("DEPRECATION")
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class WanTaskDescDelegate : TaskDescriptionDelegate {

    override fun getTaskDescription(oldTaskDescription: ActivityManager.TaskDescription?): ActivityManager.TaskDescription? {
        if (oldTaskDescription == null) {
            return null
        }
        val labelPrefix = "[" + VUserManager.get().userName + "] "
        val oldLabel =
            if (oldTaskDescription.label != null) oldTaskDescription.label else ""

        return if (!oldLabel.startsWith(labelPrefix)) { // Is it really necessary?
            ActivityManager.TaskDescription(
                labelPrefix + oldTaskDescription.label,
                oldTaskDescription.icon,
                oldTaskDescription.primaryColor
            )
        } else {
            oldTaskDescription
        }
    }

}