package com.xhe.toastdemo

import com.qyf.toast.intef.IToastContentIntercept

/**
 * Toast 内容拦截器
 * @author qinyafei
 * @since 2020/10/20 10:56
 */
class ToastContentIntercept : IToastContentIntercept {

    /**
     * @param msg CharSequence? 当前需要显示的内容
     * @return Boolean true 内容可以显示，false 丢弃
     */
    override fun intercept(msg: CharSequence?): Boolean {

        return true
    }
}