package com.qyf.toast.utils

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.qyf.toast.DialogToast

/**
 * 生命周期监听
 * @author qinyafei
 * @since 2020/9/10 13:48
 */
class LifeCycleListener : Application.ActivityLifecycleCallbacks {
    private var dialogToast: DialogToast? = null //
    var currrentActivity: Activity? = null // 栈顶Activity

    internal constructor(application: Application, dialogToast: DialogToast) {
        application.registerActivityLifecycleCallbacks(this)
        this.dialogToast = dialogToast
    }

    companion object {
        var currentShowActivity: Activity? = null
    }

    override fun onActivityPaused(p0: Activity) {
        if (p0 == dialogToast?.view?.context && !p0.isFinishing) {
            dialogToast?.cancel()
        }
    }

    override fun onActivityStarted(p0: Activity) {
        currentShowActivity = p0
    }

    override fun onActivityDestroyed(p0: Activity) {
        if (currentShowActivity === p0) { //销毁引用，避免内存泄漏
            currentShowActivity = null
        }
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {

    }

    override fun onActivityStopped(p0: Activity) {

    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
        currrentActivity = p0
    }

    override fun onActivityResumed(p0: Activity) {
        currentShowActivity = p0
    }
}