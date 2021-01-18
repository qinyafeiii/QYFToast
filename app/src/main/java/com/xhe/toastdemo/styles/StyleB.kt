package com.xhe.toastdemo.styles

import android.graphics.Color
import android.view.Gravity
import com.qyf.toast.basic.BaseStyle

class StyleB : BaseStyle() {
    override fun getTextColor(): Int {
        return Color.RED
    }

    override fun getBackgroungColor(): Int {
        return Color.YELLOW
    }

    override fun getGravity(): Int {
        return Gravity.CENTER
    }

    override fun getTextSize(): Float {
        return 20f
    }
}