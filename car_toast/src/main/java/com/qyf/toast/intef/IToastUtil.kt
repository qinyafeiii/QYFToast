package com.qyf.toast.intef

import android.view.View
import com.qyf.toast.ToastDuration
import com.qyf.toast.ToastReplaceType


/**
 * toast 处理接口
 * @author qinyafei
 * @since 2020/9/10 14:58
 */
interface IToastUtil {

    /**
     * 根据初始化配置样式显示Toast
     * 默认显示时间 Toast Short
     * @param msg CharSequence
     */
    fun show(msg: CharSequence)


    /**
     * Toast 提示
     * @param msg CharSequence toast 内容
     * @param duration Int 时间
     */
    fun show(msg: CharSequence, @ToastDuration duration: Int)

    /**
     * 根据初始化配置样式显示Toast
     * @param msg CharSequence 显示内容
     * @param duration Int 显示样式
     * @param replaceType Int 显示模式
     */
    fun show(msg: CharSequence, @ToastDuration duration: Int, @ToastReplaceType replaceType: Int)

    /**
     * 自定义toast样式显示toast
     * @param msg CharSequence 显示内容
     * @param style IToastStyle 显示样式
     * @param duration Int 显示时间
     * @param replaceType Int 显示模式
     */
    fun show(msg: CharSequence, @ToastDuration duration: Int, @ToastReplaceType replaceType: Int, style: IToastStyle)

    /**
     * 自定义Toast布局
     * @param msg CharSequence 显示内容
     * @param style IToastStyle 显示样式。注意：自定义布局，仅仅支持的Gravity,xoffset,yoffset属性值的修改
     * @param selfView View 自定义的View
     * @param duration Int 显示时间
     * @param replaceType Int 显示模式
     */
    fun show(msg: CharSequence, @ToastDuration duration: Int, @ToastReplaceType replaceType: Int, style: IToastStyle, selfView: View)
}