package com.xhe.toastdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_seconds.*

/**
 * Created by hexiang on 2018/2/6.
 */
class SecondsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seconds)
    }

    override fun onResume() {
        super.onResume()
        Log.d("PToast", javaClass.simpleName + "-------onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("PToast", javaClass.simpleName + "-------onRestart")
    }

    override fun onPause() {
        Log.d("PToast", javaClass.simpleName + "-------onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("PToast", javaClass.simpleName + "-------onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("PToast", javaClass.simpleName + "-------onDestroy")
        super.onDestroy()
    }
}