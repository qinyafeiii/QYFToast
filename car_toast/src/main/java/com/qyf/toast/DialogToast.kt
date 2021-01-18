package com.qyf.toast

import android.app.Application
import android.view.Gravity
import android.view.View
import com.qyf.toast.basic.BaseToast
import com.qyf.toast.utils.DialogUtil
import com.qyf.toast.utils.LifeCycleListener
import java.lang.Exception

/**
 * 使用WindowManger实现Toast显示。使用场景：
 * 1. 当前手机无通知栏权限
 * 2. 安卓11 不支持toast自定义布局
 * @constructor
 */
internal class DialogToast(application: Application) : BaseToast(application) {

    private var mToastView: View? = null
    private var mGravity: Int = Gravity.CENTER
    private var mXOffset: Int = 0
    private var mYOffset: Int = 0

    private var mLifeCycleListener: LifeCycleListener? = null
    override fun setView(view: View?) {
        mToastView = view
        updateContentTextView(findToastTextView(view))
    }

    init {
        mLifeCycleListener = LifeCycleListener(application, this)
    }

    /**
     * 收集布局信息给Dialog 使用
     * @param gravity Int
     * @param xOffset Int
     * @param yOffset Int
     */
    override fun setGravity(gravity: Int, xOffset: Int, yOffset: Int) {
        mGravity = gravity
        mXOffset = xOffset
        mYOffset = yOffset
    }

    override fun show() {
        mToastView?.let {
            if (LifeCycleListener.currentShowActivity == null || LifeCycleListener.currentShowActivity!!.isFinishing) return
            DialogUtil.showSelfDialog(LifeCycleListener.currentShowActivity!!, it, mGravity, mXOffset, mYOffset)
        }
    }

    override fun cancel() {
        if (DialogUtil.dialog?.isShowing!!) {
            try {
                DialogUtil.dialog?.dismiss()
            } catch (e: Exception) { // 捕获可能的异常：View=DecorView@7d8b3aa[] not attached to window manager
            }
        }
    }

    override fun getView(): View? = mToastView
}