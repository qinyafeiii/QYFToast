package com.qyf.toast.intef

import android.graphics.drawable.Drawable
import android.widget.TextView
import androidx.annotation.ColorInt

interface IToastStyle {


    /**
     * 文字大小
     * @return Float
     */
    fun getTextSize(): Float

    /**
     * 字体颜色
     * @return Int
     */
    @ColorInt
    fun getTextColor(): Int

    /**
     * paddingStart
     * @return Int
     */
    fun getTextPaddingStart(): Int

    /**
     * paddingEnd
     * @return Int
     */
    fun getTextPaddingEnd(): Int

    /**
     * paddingTop
     * @return Int
     */
    fun getTextPaddingTop(): Int

    /**
     * paddingBottom
     * @return Int
     */
    fun getTextPaddingBottom(): Int

    /**
     * 背景颜色，背景图GradientDrawable
     * @return Int
     */
    @ColorInt
    fun getBackgroungColor(): Int

    /**
     * 获取背景透明度
     * @return Int
     */
    fun getBackgroundAlpha(): Int

    /**
     * 获取别境圆角
     * @return Int
     */
    fun getBackgroundCornerRadious(): Float

    /**
     * 显示位置
     * @return Int
     */
    fun getGravity(): Int

    /**
     * 相较于重心的X轴偏移量
     * @return Int
     */
    fun getXOffset(): Int

    /**
     * 相较于重心，y轴的偏移量
     * @return Int
     */
    fun getYOffset(): Int

    // 以下方法返回不为null,则上面相关属性设置失效
    /**
     * 获取TextView
     * @return TextView
     */
    fun getTextView(): TextView?

    /**
     * 获取背景图
     * @return Drawable
     */
    fun getBackgroundDrawable(): Drawable?

}