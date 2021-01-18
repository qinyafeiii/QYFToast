package com.qyf.toast.intef

/**
 * Toast 内容拦截器，用于统一拦截内容，控制内容是否显示
 * @author qinyafei
 * @since 2020/9/10 13:50
 */
interface IToastContentIntercept {
    /**
     * 拦截器
     * @param msg String 需要显示的内容
     * @return Boolean true：可以显示，false 不能显示
     */
    fun intercept(msg: CharSequence?): Boolean
}