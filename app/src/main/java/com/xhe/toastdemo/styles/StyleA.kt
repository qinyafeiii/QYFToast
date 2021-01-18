package com.xhe.toastdemo.styles

import android.graphics.Color
import android.view.Gravity
import com.qyf.toast.basic.BaseStyle

class StyleA : BaseStyle() {
    override fun getTextColor(): Int {
        return Color.RED
    }

    override fun getBackgroungColor(): Int {
        return Color.GREEN
    }

    override fun getGravity(): Int {
        return Gravity.CENTER
    }

    override fun getTextSize(): Float {
        return 20f
    }
}