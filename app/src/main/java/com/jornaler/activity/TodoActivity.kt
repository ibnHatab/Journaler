package com.jornaler.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jornaler.R

class TodoActivity : ItemActivity() {
    override val tag: String = "Tod Activity"
    override fun getLayout(): Int = R.layout.activity_todo

}