package com.jornaler.activity

import com.jornaler.R

class TodoActivity : ItemActivity() {
    override val tag: String = "Tod Activity"
    override fun getLayout(): Int = R.layout.activity_todo

}