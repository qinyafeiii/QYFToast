package com.qyf.toast

import androidx.annotation.IntDef
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * 显示时长
 */
@IntDef(QYFToast.LENGTH_LONG, QYFToast.LENGTH_SHORT)
@Retention(RetentionPolicy.SOURCE)
annotation class ToastDuration

/**
 * 显示模式
 */
@IntDef(QYFToast.DISCARD, QYFToast.REPLACE_NOW, QYFToast.REPLACE_BEHIND)
@Retention(RetentionPolicy.SOURCE)
annotation class ToastReplaceType