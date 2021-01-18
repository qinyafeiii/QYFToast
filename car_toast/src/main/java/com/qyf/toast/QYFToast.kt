package com.qyf.toast

import android.app.Application
import android.view.View
import android.widget.Toast
import com.qyf.toast.basic.BaseStyle
import com.qyf.toast.basic.BaseToastContentIntercept
import com.qyf.toast.intef.IToastContentIntercept
import com.qyf.toast.intef.IToastUtil
import com.qyf.toast.intef.IToastStyle
import com.qyf.toast.bean.SingleToastTask
import com.qyf.toast.utils.ToastHelper
import java.lang.IllegalArgumentException

/**
 * Toast 工具类 单例模式
 * @author qinyafei
 * @since 2020/9/10 13:44
 */
object QYFToast : IToastUtil {

    /**
     * 显示时间
     */
    const val LENGTH_SHORT = 2000 // 长Toast 显示时间
    const val LENGTH_LONG = 3500 // 短Toast 显示时间

    /**
     * 显示中再次请求替换方式
     */
    const val DISCARD = 0 //如果队列中有消息，直接丢弃
    const val REPLACE_NOW = 1 // 立即显示,并清空之前未显示的Toast
    const val REPLACE_BEHIND = 2 // 排队等待

    /*
        初始化时配置的默认样式
     */
    private lateinit var mDefaultStyle: IToastStyle

    /*
        App中唯一的Toast示例
     */
    private var mToast: Toast? = null

    /*
       Toast任务处理者
     */
    private lateinit var mToastHandler: ToastHandler

    /*
       toast 内容拦截器
     */
    private lateinit var mToastContentIntercept: IToastContentIntercept


    /**
     * 初始化方法
     * @param application Application
     * @param style IToastStyle?
     */
    @JvmStatic
    fun init(application: Application, style: IToastStyle? = BaseStyle(), toastContentIntercept: IToastContentIntercept? = BaseToastContentIntercept()) {
        this.mDefaultStyle = style ?: BaseStyle()
        mToast = ToastHelper.getTargetToast(application)
        mToastHandler = ToastHandler(application, mToast!!)
        mToastContentIntercept = toastContentIntercept ?: BaseToastContentIntercept()
    }


    override fun show(msg: CharSequence) {
        val toastTask = ToastHelper.getToastTask(msg = msg, toastStyle = mDefaultStyle)
        showToast(toastTask)
    }

    override fun show(msg: CharSequence, duration: Int) {
        val toastTask = ToastHelper.getToastTask(msg = msg, duration = duration, toastStyle = mDefaultStyle)
        showToast(toastTask)
    }

    override fun show(msg: CharSequence, duration: Int, replaceType: Int) {
        val toastTask = ToastHelper.getToastTask(msg, duration, replaceType, mDefaultStyle)
        showToast(toastTask)
    }

    override fun show(msg: CharSequence, duration: Int, replaceType: Int, style: IToastStyle) {
        val toastTask = ToastHelper.getToastTask(msg = msg, duration = duration, replaceType = replaceType, toastStyle = style)
        showToast(toastTask)
    }

    override fun show(msg: CharSequence,  duration: Int, replaceType: Int,style: IToastStyle, selfView: View) {
        val toastTask = ToastHelper.getToastTask(msg = msg, duration = duration, replaceType = replaceType, toastStyle = style, customView = selfView)
        showToast(toastTask)
    }

    /**
     * 执行Toast任务
     * @param task SingleToastTask
     */
    private fun showToast(task: SingleToastTask) {
        checkInit()
        if (!mToastContentIntercept.intercept(task.content))
            return // 未通过拦截，不执行显示
        mToastHandler.addTask(task)// 添加一次显示任务
    }

    /**
     * 初始化检查
     */
    private fun checkInit() {
        if (mToast == null) {
            throw IllegalArgumentException("toast 未进行初始化！！")
        }
    }

    /**
     * 清空任务队列
     */
    @JvmStatic
    fun clearAllTask() {
        mToastHandler.clearTask()
    }
}