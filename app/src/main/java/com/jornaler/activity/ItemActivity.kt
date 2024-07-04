package com.jornaler.activity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.jornaler.R
import com.jornaler.model.MODE

abstract class ItemActivity : BaseActivity() {
    private var mode = MODE.VIEW
    private var success = Activity.RESULT_CANCELED

    override fun getActivityTitle() = R.string.app_name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = intent.data
        data?.let {
            val modeToSet = intent.getIntExtra(MODE.EXTRAS_KEY, MODE.VIEW.mode)
            mode = MODE.getByValue(modeToSet)
        }
        Log.v(tag, "Mode [ $mode ]")
    }

    override fun onDestroy() {
        super.onDestroy()
        setResult(success)
    }
}