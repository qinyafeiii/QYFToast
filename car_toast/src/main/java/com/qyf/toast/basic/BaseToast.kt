package com.qyf.toast.basic

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.qyf.toast.utils.ToastHelper

/**
 * Toast 基类
 * @author qinyafei
 * @since 2020/9/10 13:44
 */
open class BaseToast(context: Context) : Toast(context.applicationContext) {

    /*
        用于显示内容的TextView
     */
    private var contentTextView: TextView? = null

    override fun setView(view: View?) {
        updateContentTextView(findToastTextView(view))
        super.setView(view)
    }

    override fun setText(s: CharSequence?) {
        contentTextView?.text = s
    }

    /**
     * 获取布局中的TextView
     * @param view View?
     * @return TextView?
     */
    fun findToastTextView(view: View?): TextView? {
        return ToastHelper.findToastTextView(view)
    }

    fun updateContentTextView(contentTextView: TextView?) {
        if (contentTextView == null) {
            throw IllegalArgumentException("yourself toast must contain a TextView")
        }
        this.contentTextView = contentTextView
    }
}
