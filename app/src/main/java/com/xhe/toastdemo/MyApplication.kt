package com.xhe.toastdemo

import android.app.Application
import com.qyf.toast.QYFToast
import com.xhe.toastdemo.styles.StyleA

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        QYFToast.init(
                application = this,
                style = StyleA(),
                toastContentIntercept = ToastContentIntercept()
        )
    }
}