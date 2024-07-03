package com.jornaler.activity

import com.jornaler.R

class NoteActivity : ItemActivity() {
    override val tag: String = "Note Activity"
    override fun getLayout(): Int = R.layout.activity_note

}