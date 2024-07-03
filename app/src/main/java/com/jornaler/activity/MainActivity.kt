package com.jornaler.activity
import com.jornaler.R


class MainActivity : BaseActivity() {
    override  val tag = "Main Activity"
    override fun getLayout(): Int = R.layout.activity_main
    override fun getActivityTitle() = R.string.app_name
}