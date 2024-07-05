package com.journaler.activity

import android.os.Bundle
import android.widget.Button
import com.journaler.R

class TodoActivity : ItemActivity() {

    companion object {
        val EXTRA_DATE = "EXTRA_DATE"
        val EXTRA_TIME = "EXTRA_TIME"
    }

    override val tag = "Todo activity"

    override fun getLayout() = R.layout.activity_todo
    override fun getToolbar(): Int = R.id.todo_toolbar

    private lateinit var pick_date: Button
    private lateinit var pick_time: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pick_date = findViewById(R.id.pick_date)

        val data = intent.extras
        data?.let {
            val date = data.getString(EXTRA_DATE, "")
            val time = data.getString(EXTRA_TIME, "")
            pick_date.text = date
            pick_time.text = time
        }
    }

}