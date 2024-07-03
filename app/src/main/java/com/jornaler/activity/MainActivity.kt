package com.jornaler.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jornaler.R
import com.jornaler.Journaler

class MainActivity : AppCompatActivity() {
    private val tag = Journaler.tag

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
        Log.v(tag, "[ACT ON CREATE ]")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(tag, "[ACT ON DESTROY]")
    }


}