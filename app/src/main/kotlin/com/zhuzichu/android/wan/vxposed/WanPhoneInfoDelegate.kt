package com.zhuzichu.android.wan.vxposed

import com.lody.virtual.client.hook.delegate.PhoneInfoDelegate

class WanPhoneInfoDelegate : PhoneInfoDelegate{

    override fun getMacAddress(oldMacAddress: String, userId: Int): String {
        return oldMacAddress
    }

    override fun getBluetoothAddress(oldBluetoothAddress: String, userId: Int): String {
        return oldBluetoothAddress
    }

    override fun getDeviceId(oldDeviceId: String, userId: Int): String {
        return oldDeviceId
    }
}