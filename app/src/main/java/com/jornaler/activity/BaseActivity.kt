package com.jornaler.activity

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.jornaler.R


abstract class BaseActivity : FragmentActivity() {
    protected abstract val tag: String
    protected abstract fun getLayout(): Int
    protected abstract fun getActivityTitle(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        val activityTitle: TextView = findViewById(R.id.activity_title)
        activityTitle.setText(getActivityTitle())
        Log.v(tag, "[ ON CREATE ]")
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        Log.v(tag, "[ ON POST CREATE ]")
    }

    override fun onRestart() {
        super.onRestart()
        Log.v(tag, "[ ON RESTART ]")
    }

    override fun onStart() {
        super.onStart()

        Log.v(tag, "[ ON START ]")
    }

    override fun onResume() {
        super.onResume()
        Log.v(tag, "[ ON RESUME ]")
    }

    override fun onPostResume() {
        super.onPostResume()
        Log.v(tag, "[ ON POST RESUME ]")
    }

    override fun onPause() {
        super.onPause()
        Log.v(tag, "[ ON PAUSE ]")
    }

    override fun onStop() {
        super.onStop()
        Log.v(tag, "[ ON STOP ]")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(tag, "[ ON DESTROY ]")
    }


}
