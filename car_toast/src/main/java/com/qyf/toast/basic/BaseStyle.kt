package com.qyf.toast.basic

import android.graphics.drawable.Drawable
import android.widget.TextView
import com.qyf.toast.Constants
import com.qyf.toast.intef.IToastStyle

/**
 * 基础样式，默认样式
 * @author qinyafei
 * @since 2020/9/10 13:50
 */
open class BaseStyle : IToastStyle {
    override fun getTextColor(): Int {
        return Constants.TEXT_COLOR
    }

    override fun getTextSize(): Float {
        return Constants.TEXT_SIZE
    }

    override fun getTextPaddingStart(): Int {
        return Constants.TEXT_PADDING_START
    }

    override fun getTextPaddingEnd(): Int {
        return Constants.TEXT_PADDING_END
    }

    override fun getTextPaddingTop(): Int {
        return Constants.TEXT_PADDING_TOP
    }

    override fun getTextPaddingBottom(): Int {
        return Constants.TEXT_PADDING_BOTTOM
    }

    override fun getBackgroungColor(): Int {
        return Constants.BG_COLOR
    }

    override fun getBackgroundAlpha(): Int {
        return Constants.BG_ALPHA
    }

    override fun getBackgroundCornerRadious(): Float {
        return Constants.BG_CORNER_RADIOUS
    }

    override fun getGravity(): Int {
        return Constants.GRAVITY
    }

    override fun getXOffset(): Int {
        return Constants.X_OFFSET
    }

    override fun getYOffset(): Int {
        return Constants.Y_OFFSET
    }

    override fun getTextView(): TextView? {
        return null
    }

    override fun getBackgroundDrawable(): Drawable? {
        return null
    }
}