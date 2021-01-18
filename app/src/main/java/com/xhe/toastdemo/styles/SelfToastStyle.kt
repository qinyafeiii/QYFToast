package com.xhe.toastdemo.styles
import android.view.Gravity
import com.qyf.toast.basic.BaseStyle
/**
 *
 */
class SelfToastStyle : BaseStyle() {
    override fun getGravity(): Int {
        return Gravity.CENTER
    }

    override fun getXOffset(): Int {
        return 0
    }

    override fun getYOffset(): Int {
        return 0
    }
}