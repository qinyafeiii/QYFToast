package com.xhe.toastdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import com.qyf.toast.QYFToast
import com.xhe.toastdemo.styles.SelfToastStyle
import com.xhe.toastdemo.styles.StyleB
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_default.setOnClickListener {
            QYFToast.show("我是默认Toast样式")
        }

        tv1.setOnClickListener {
            for (i in 0..4) {
                if (i == 2) {
                    QYFToast.show("$i", QYFToast.LENGTH_SHORT, QYFToast.REPLACE_BEHIND, StyleB())
                } else {
                    QYFToast.show("$i", QYFToast.LENGTH_SHORT, QYFToast.REPLACE_BEHIND)
                }
            }
        }

        tv_self.setOnClickListener {// 显示自定义Toast布局
            val toastView = LayoutInflater.from(this).inflate(R.layout.toast_view, null)
            QYFToast.show("自定义Toast布局显示", QYFToast.LENGTH_SHORT, QYFToast.REPLACE_NOW, SelfToastStyle(), toastView)
        }

        tv2.setOnClickListener {
            QYFToast.show("我是立即显示的Toast", QYFToast.LENGTH_SHORT, QYFToast.REPLACE_NOW)
        }
        tv3.setOnClickListener {
            QYFToast.show("我是排队显示的Toast", QYFToast.LENGTH_SHORT, QYFToast.REPLACE_BEHIND)
        }

        tv_childThread.setOnClickListener {
            Thread(Runnable {
                QYFToast.show("hello world1", QYFToast.LENGTH_SHORT, QYFToast.DISCARD)
            }).start()
            QYFToast.show("hello world1", QYFToast.LENGTH_SHORT, QYFToast.DISCARD)
        }
    }
}
