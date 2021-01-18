package com.qyf.toast.utils

import android.R
import android.app.AppOpsManager
import android.app.Application
import android.app.NotificationManager
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.qyf.toast.QYFToast
import com.qyf.toast.DialogToast
import com.qyf.toast.SafeToast
import com.qyf.toast.basic.BaseStyle
import com.qyf.toast.basic.BaseToast
import com.qyf.toast.intef.IToastStyle
import com.qyf.toast.bean.SingleToastTask
import java.lang.reflect.InvocationTargetException

object ToastHelper {
    /**
     * 获取目标的Toast
     * @param application Application
     * @return Toast
     */
    fun getTargetToast(application: Application): Toast {
        return when {
            !areNotificationsEnabled(application) -> { //没有通知栏权限，使用Dialog实现
                DialogToast(application)
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O + 4 -> {// 安卓11 不支持自定义Toast 布局,使用Dialog实现
                DialogToast(application)
            }
            Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1 -> { // 安卓7.1.1,存在Toast崩溃的问题,使用加固后的Toast
                SafeToast(application)
            }
            else -> {
                BaseToast(application)
            }
        }
    }

    /**
     * 检查通知栏权限是否开启
     * @param context Context
     * @return Boolean true 通知栏权限开启，false 通知栏权限未开启
     */
    private fun areNotificationsEnabled(context: Context): Boolean {
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
                val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                manager != null && manager.areNotificationsEnabled()
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT -> {
                val appOps = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
                val appInfo = context.applicationInfo
                val packageName = context.applicationContext.packageName
                val uid = appInfo.uid
                try {
                    val appOpsClass = Class.forName(AppOpsManager::class.java.name)
                    val checkOpNoThrowMethod = appOpsClass.getMethod("checkOpNoThrow", Integer.TYPE, Integer.TYPE, String::class.java)
                    val opPostNotificationValue = appOpsClass.getDeclaredField("OP_POST_NOTIFICATION")
                    val value = opPostNotificationValue[Int::class.java] as Int
                    checkOpNoThrowMethod.invoke(appOps, value, uid, packageName) as Int == AppOpsManager.MODE_ALLOWED
                } catch (ignored: ClassNotFoundException) {
                    true
                } catch (ignored: NoSuchMethodException) {
                    true
                } catch (ignored: NoSuchFieldException) {
                    true
                } catch (ignored: InvocationTargetException) {
                    true
                } catch (ignored: IllegalAccessException) {
                    true
                } catch (ignored: RuntimeException) {
                    true
                }
            }
            else -> {
                true
            }
        }
    }


    /**
     * 获取用于显示消息的 TextView
     * 取用顺序：
     *  1.View 是否是TextView,如果是则直接取用，否则进入下一步
     *  2.查找id 为 android.R.id.message 的TextView，查找到则取用，否则进入下一步
     *  3.查找不到id 为 android.R.id.message 的TextView,
     *  如果以上步骤都查找不到，则返回null
     */
    fun findToastTextView(view: View?): TextView? {
        return when {
            view is TextView -> {
                return view
            }
            view is ViewGroup && view.findViewById<View>(android.R.id.message) is TextView -> {
                view.findViewById<View>(R.id.message) as TextView
            }
            view is ViewGroup -> {
                findTextViewInViewGroup(view)
            }
            else -> {
                null
            }
        }
    }

    /**
     * 查找ViewGroup内的TextView：使用第一个查找到的TextView 显示信息
     * @param group ViewGroup
     * @return TextView?
     */
    private fun findTextViewInViewGroup(group: ViewGroup): TextView? {
        for (i in 0 until group.childCount) {
            val view = group.getChildAt(i)
            if (view is TextView) {
                return view
            } else if (view is ViewGroup) {
                val textView = findTextViewInViewGroup(view)
                if (textView != null) {
                    return textView
                }
            }
        }
        return null
    }

    /**
     * 获取Toast布局
     * @param msg CharSequence
     * @param duration Int
     * @param replaceType Int
     * @param toastStyle IToastStyle
     * @param customView View?
     * @return SingleToastTask
     */
    fun getToastTask(msg: CharSequence, duration: Int = QYFToast.LENGTH_SHORT, replaceType: Int = QYFToast.REPLACE_BEHIND, toastStyle: IToastStyle = BaseStyle(), customView: View? = null): SingleToastTask {
        return SingleToastTask(msg, duration, replaceType, toastStyle, customView)
    }

    /**
     * 根据样式创建TextView
     * @param toastTask SingleToastTask
     * @return View
     */
    fun createToastTextView(context: Context, toastStyle: IToastStyle): View? {
        val textView = TextView(context)
        textView.textSize = toastStyle.getTextSize()
        textView.setTextColor(toastStyle.getTextColor())
        textView.setPadding(toastStyle.getTextPaddingStart(),
                toastStyle.getTextPaddingTop()
                , toastStyle.getTextPaddingEnd()
                , toastStyle.getTextPaddingBottom()
        )
        val bgDrawable = GradientDrawable().apply {
            cornerRadius = toastStyle.getBackgroundCornerRadious()
            setColor(toastStyle.getBackgroungColor())
            alpha = toastStyle.getBackgroundAlpha()
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            textView.background = bgDrawable
        } else {
            textView.setBackgroundDrawable(bgDrawable)
        }
        return textView;
    }

}