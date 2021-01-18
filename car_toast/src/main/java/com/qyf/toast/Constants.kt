package com.qyf.toast

import android.graphics.Color
import android.view.Gravity

/**
 * 默认属性配置:
 * 文本大小/宽高单位：px
 * @author qinyafei
 * @since 2020/9/10 13:42
 */
internal interface Constants {
    companion object {

        const val TEXT_SIZE: Float = 14f // 单位sp

        const val TEXT_COLOR = Color.WHITE

        const val TEXT_PADDING_START: Int = 8

        const val TEXT_PADDING_TOP: Int = 5

        const val TEXT_PADDING_END: Int = 8

        const val TEXT_PADDING_BOTTOM: Int = 5

        const val BG_CORNER_RADIOUS: Float = 15f

        const val BG_COLOR: Int = Color.BLACK

        const val BG_ALPHA: Int = 200

        const val GRAVITY = Gravity.BOTTOM

        const val X_OFFSET = 0 // X轴偏移量

        const val Y_OFFSET = 100 // Y轴偏移量。正数表示向上移动，负数表示向下移动

        const val MSG_QUEUE_CAPACITY = 10 //任务队列容量
    }
}