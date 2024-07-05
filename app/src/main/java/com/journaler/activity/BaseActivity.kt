package com.journaler.activity

import android.Manifest
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.journaler.R
import com.journaler.extension.getAnimation
import com.journaler.permission.PermissionCompatActivity


abstract class BaseActivity : PermissionCompatActivity() {

    companion object {
        val REQUEST_GPS = 0

        private var fontExoBold: Typeface? = null
        private var fontExoRegular: Typeface? = null

        fun applyFonts(view: View, ctx: Context) {
            var vTag = ""
            if (view.tag is String) {
                vTag = view.tag as String
            }
            when (view) {
                is ViewGroup -> {
                    for (x in 0..view.childCount - 1) {
                        applyFonts(view.getChildAt(x), ctx)
                    }
                }

                is Button -> {
                    when (vTag) {
                        ctx.getString(R.string.tag_font_bold) -> {
                            view.typeface = fontExoBold
                        }

                        else -> {
                            view.typeface = fontExoRegular
                        }
                    }
                }

                is TextView -> {
                    when (vTag) {
                        ctx.getString(R.string.tag_font_bold) -> {
                            view.typeface = fontExoBold
                        }

                        else -> {
                            view.typeface = fontExoRegular
                        }
                    }
                }

                is EditText -> {
                    when (vTag) {
                        ctx.getString(R.string.tag_font_bold) -> {
                            view.typeface = fontExoBold
                        }

                        else -> {
                            view.typeface = fontExoRegular
                        }
                    }
                }
            }
        }
    }


    protected abstract val tag: String
    protected abstract fun getLayout(): Int
    protected abstract fun getActivityTitle(): Int

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        setContentView(getLayout())

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        Log.v(tag, "[ ON CREATE ]")
        requestPermissions(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
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
        val anim = getAnimation(R.anim.top_to_bottom)
        findViewById<Toolbar>(R.id.toolbar).startAnimation(anim)
    }

    override fun onPostResume() {
        super.onPostResume()
        Log.v(tag, "[ ON POST RESUME ]")
    }

    override fun onPause() {
        super.onPause()
        Log.v(tag, "[ ON PAUSE ]")
        val animation = getAnimation(R.anim.hide_to_top)
        findViewById<Toolbar>(R.id.toolbar).startAnimation(animation)
    }

    override fun onStop() {
        super.onStop()
        Log.v(tag, "[ ON STOP ]")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(tag, "[ ON DESTROY ]")
    }

    protected fun applyFonts() {
        initFonts()
        Log.v(tag, "Applying fonts [ START ]")
        val rootView = findViewById<ViewGroup>(android.R.id.content)
        applyFonts(rootView, this)
        Log.v(tag, "Applying fonts [ END ]")
    }

    private fun initFonts() {
        if (fontExoBold == null) {
            Log.v(tag, "Initializing font [ Exo2-Bold ]")
            fontExoBold = Typeface.createFromAsset(assets, "fonts/Exo2-Bold.ttf")
        }
        if (fontExoRegular == null) {
            Log.v(tag, "Initializing font [ Exo2-Regular ]")
            fontExoRegular = Typeface.createFromAsset(assets, "fonts/Exo2-Regular.ttf")
        }
    }
}

