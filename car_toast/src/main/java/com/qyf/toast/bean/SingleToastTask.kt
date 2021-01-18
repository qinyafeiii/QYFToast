package com.qyf.toast.bean

import android.view.View
import com.qyf.toast.basic.BaseStyle
import com.qyf.toast.intef.IToastStyle

/**
 * toast 任务bean
 * @author qinyafei
 * @since 2020/9/10 14:29
 */
data class SingleToastTask(val content: CharSequence, // 需要显示的内容
                           val duration: Int,// 停留时间
                           val replaceType: Int,// 显示方式：直接显示；排队等待；如果正在显示，直接丢弃，
                           val toastStyle: IToastStyle = BaseStyle(), // 当前消息Toast 样式
                           val customView: View? = null // 当前消息布局
)