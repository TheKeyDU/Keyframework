package com.maosong.component.Base

interface XPermissionListener {
    fun onGranted()

    fun onDenied(deniedPermissions: List<String>)
}