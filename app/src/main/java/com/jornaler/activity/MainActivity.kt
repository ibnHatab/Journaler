package com.jornaler.activity
import android.os.Bundle
import com.jornaler.R
import com.journaler.fragment.ItemsFragment


class MainActivity : BaseActivity() {
    override  val tag = "Main Activity"
    override fun getLayout(): Int = R.layout.activity_main
    override fun getActivityTitle() = R.string.app_name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragment = ItemsFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit()
    }
}