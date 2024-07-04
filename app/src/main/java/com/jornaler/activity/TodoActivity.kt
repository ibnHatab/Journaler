package com.jornaler.activity

import android.os.Bundle
import android.widget.Button
import com.jornaler.R

class TodoActivity : ItemActivity() {

    companion object {
        const val EXTRA_DATE = "EXTRA_DATE"
        const val EXTRA_TIME = "EXTRA_TIME"
    }

    override val tag: String = "Tod Activity"
    override fun getLayout(): Int = R.layout.activity_todo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = intent.extras
        data?.let {
            val date = data.getString(EXTRA_DATE, "")
            val time = data.getString(EXTRA_TIME, "")
            findViewById<Button>(R.id.pick_date).text = date
            findViewById<Button>(R.id.pick_time).text = time
        }
    }

}