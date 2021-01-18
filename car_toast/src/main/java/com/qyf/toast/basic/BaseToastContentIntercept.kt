package com.qyf.toast.basic

import com.qyf.toast.intef.IToastContentIntercept

/**
 * 默认Toast 内容拦截器
 * @author qinyafei
 * @since 2020/9/10 13:53
 */
class BaseToastContentIntercept : IToastContentIntercept {
    override fun intercept(msg: CharSequence?): Boolean {
        return !msg.isNullOrEmpty()
    }
}