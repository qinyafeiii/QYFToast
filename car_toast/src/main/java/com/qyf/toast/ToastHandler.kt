package com.qyf.toast

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Toast
import com.qyf.toast.bean.SingleToastTask
import com.qyf.toast.utils.ToastHelper
import com.qyf.toast.QYFToast.DISCARD
import com.qyf.toast.QYFToast.REPLACE_NOW
import java.util.*
import java.util.concurrent.ArrayBlockingQueue

/**
 * Toast 消息处理类：传入Looper.getMainLooper()，指定主线程。方便线程切换
 * @author qinyafei
 * @since 2020/9/10 13:46
 */
internal class ToastHandler(private val context: Context, private val mToast: Toast) : Handler(Looper.getMainLooper()) {

    companion object {
        const val SHOW = 1 // 展示toast
        const val CONTINUE = 2 // 继续
        const val CLEAR = 3 // 清空任务，仅在绑定页面周期的时候触发(页面绑定相关的待开发)
    }

    /**
     * 延迟显示时间
     */
    private val DELAY_TIMEOUT = 200L

    private var isShowing = false // 是否正在有Toast显示,true 是

    /*
       任务队列
     */
    private val toastTasksQueue: Queue<SingleToastTask> = ArrayBlockingQueue(Constants.MSG_QUEUE_CAPACITY)

    /**
     * 上一个Toast样式的全类名
     */
    private var lastStyleClazz: String? = ""

    /**
     * 增加显示任务
     * @param task SingleToastTask
     */
    fun addTask(task: SingleToastTask) {
        updateQueuByMsgReplaceType(task)
        if (!isShowing) {
            isShowing = true
            sendEmptyMessageDelayed(SHOW, DELAY_TIMEOUT) // 延时显示，避免show之后立即调用Finish,无法显示当前信息
        }
    }

    /**
     * 更新消息队列的内容
     * @param task SingleToastTask
     */
    private fun updateQueuByMsgReplaceType(task: SingleToastTask) {
        when (task.replaceType) {
            DISCARD -> { // 如果队列中仍有消息显示，则丢弃
                if (isShowing)
                    return
            }
            REPLACE_NOW -> { //立即显示：清空已有消息队列
                if (isShowing) {
                    mToast.cancel()
                    isShowing = false
                    removeCallbacksAndMessages(null) // 清空所有信息
                }
                toastTasksQueue.clear()
            }
        }
        if (!toastTasksQueue.offer(task)) {// 队列已满
            toastTasksQueue.poll()// 丢弃最早的一个
            toastTasksQueue.offer(task)
        }
    }


    /**
     * 清空任务
     */
    fun clearTask() {
        isShowing = false
        toastTasksQueue.clear()
        mToast.cancel()
        removeCallbacksAndMessages(null) // 清空所有信息
    }


    override fun handleMessage(msg: Message) {
        when (msg.what) {
            SHOW -> {
                val toastTask = toastTasksQueue.peek()// 取任务
                if (toastTask != null) {
                    updateToastStyle(toastTask) // 更新Toast样式
                    mToast.setText(toastTask.content)
                    mToast.duration = if (toastTask.duration == QYFToast.LENGTH_LONG) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
                    mToast.show()
                    sendEmptyMessageDelayed(CONTINUE, toastTask.duration.toLong() + DELAY_TIMEOUT)
                } else {
                    isShowing = false
                }
            }
            CONTINUE -> {
                mToast.cancel()
                toastTasksQueue.poll()
                if (!toastTasksQueue.isEmpty()) {
                    sendEmptyMessage(SHOW)
                } else {
                    isShowing = false
                }
            }
            CLEAR -> {
                clearTask()
            }
        }
    }

    /**
     * 根据任务更新Toast的显示样式,显示的优先级别
     * 1.自定义View
     * 2.自定义样式
     * @param toastTask SingleToastTask
     */
    private fun updateToastStyle(toastTask: SingleToastTask) {
        if (lastStyleClazz == toastTask?.toastStyle::class.java.toString()) return //避免频繁更新样式，只有样式发生改变时才更新
        when {
            toastTask.customView != null -> { // 自定义布局
                mToast.view = toastTask.customView
            }
            else -> {
                mToast.view = ToastHelper.createToastTextView(context, toastTask.toastStyle)
            }
        }
        mToast.setGravity(toastTask.toastStyle.getGravity(),
                toastTask.toastStyle.getXOffset(),
                toastTask.toastStyle.getYOffset()
        )
        lastStyleClazz = toastTask?.toastStyle::class.java.toString()
    }
}