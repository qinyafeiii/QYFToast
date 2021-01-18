package com.qyf.toast

import android.content.Context
import android.os.Handler
import android.os.Message
import android.widget.Toast
import com.qyf.toast.basic.BaseToast
import java.lang.Exception

/**
 * 安全Toast，利用反射，捕获Toast过程中的异常
 * 目前已知的异常有：
 * Android 7.1.1 Toast 异常 : android.view.WindowManager$BadTokenException：Unable to add window -- token android.os.BinderProxy is not valid; is your activity running?
 * java.lang.IllegalStateException：java.lang.IllegalStateException：View android.widget.XXXX has already been added to the window manager.
 * @author qinyafei
 * @since 2020/9/9 9:54
 */
internal class SafeToast(context: Context) : BaseToast(context) {
    init {
        try {
            val mTNField = Toast::class.java.getDeclaredField("mTN")
            mTNField.isAccessible = true
            val mTN = mTNField[this]
            val mHandlerField = mTNField.type.getDeclaredField("mHandler")
            mHandlerField.isAccessible = true
            val mHandler = mHandlerField[mTN] as Handler
            mHandlerField[mTN] = SafeHandler(mHandler)
        } catch (e: Exception) {
        }
    }

    inner class SafeHandler(private val mHandler: Handler?) : Handler() {
        override fun handleMessage(msg: Message) {
            try {
                mHandler?.handleMessage(msg)
            } catch (e: Exception) {
                // android.view.WindowManager$BadTokenException：Unable to add window
                // java.lang.IllegalStateException：java.lang.IllegalStateException：View android.widget.XXXX has already been added to the window manager.
            }
        }
    }

}