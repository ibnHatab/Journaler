package com.journaler.activity

import android.Manifest
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.util.AttributeSet
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.viewbinding.ViewBinding
import com.journaler.R
import com.journaler.extension.getAnimation
import com.journaler.permission.PermissionCompatActivity

abstract class BaseActivity : PermissionCompatActivity() {

    companion object {

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

    private lateinit var toolbar: Toolbar
    protected abstract val tag: String
    protected lateinit var binding: ViewBinding

    protected abstract fun getLayout(): Int
    protected abstract fun getToolbar(): Int

    protected abstract fun getActivityTitle(): Int

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        val view = super.onCreateView(name, context, attrs)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

        setContentView(getLayout())

        toolbar = findViewById(getToolbar())
        setSupportActionBar(toolbar)

        Log.v(tag, "[ ON CREATE ]")
        requestPermissions(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        Log.v(tag, "[ ON POST CREATE ]")
        applyFonts()
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
        val animation = getAnimation(R.anim.top_to_bottom)
        findViewById<View>(getToolbar()).startAnimation(animation)
    }

    override fun onPostResume() {
        super.onPostResume()
        Log.v(tag, "[ ON POST RESUME ]")
    }

    override fun onPause() {
        super.onPause()
        Log.v(tag, "[ ON PAUSE ]")
        val animation = getAnimation(R.anim.hide_to_top)
        findViewById<View>(getToolbar()).startAnimation(animation)
    }

    override fun onStop() {
        super.onStop()
        Log.v(tag, "[ ON STOP ]")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(tag, "[ ON DESTROY ]")
    }

    private fun applyFonts() {
        initFonts()
        Log.v(tag, "Applying fonts [ START ]")
        val rootView = findViewById<View>(android.R.id.content)
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

