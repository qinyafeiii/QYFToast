package com.qyf.toast.utils

import android.app.Activity
import android.app.Dialog
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager

/**
 * 对话框工具类
 * @author qinyafei
 * @since 2020/8/28 13:50
 */
object DialogUtil {


    var dialog: Dialog? = null

    /**
     * 展示自定义对话框,实现Toast提示
     * @param context Activity
     * @param selfView View
     * @param gravity Int
     * @param offset_X Int X的偏移量
     * @param offset_Y Int Y方向的偏移量
     */
    fun showSelfDialog(context: Activity, selfView: View, gravity: Int, offset_X: Int = 0, offset_Y: Int = 0) {
        if (context == null || context.isFinishing) return
        dialog = Dialog(context)
        if (selfView.parent != null) {
            (selfView.parent as ViewGroup).removeAllViews()
        }
        dialog?.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(selfView)
            window.let { window ->
                window?.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL)
                window?.addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
                window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                window?.setBackgroundDrawableResource(android.R.color.transparent)
                window?.attributes?.gravity = gravity
                window?.attributes?.y = offset_Y
                window?.attributes?.x = offset_X
            }
            show()
        }
    }
}