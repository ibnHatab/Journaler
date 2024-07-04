package com.jornaler.activity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.jornaler.R
import com.jornaler.databinding.ActivityMainBinding
import com.journaler.fragment.ItemsFragment


class MainActivity : BaseActivity() {
    override  val tag = "Main Activity"
    override fun getLayout(): Int = R.layout.activity_main
    override fun getActivityTitle() = R.string.app_name

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        val header_binding = ActivityHeaderBinding.inflate(layoutInflater)
        findViewById<ViewPager>(R.id.pager).adapter = ViewPagerAdapter(supportFragmentManager)
    }

    @Suppress("DEPRECATION")
    private class ViewPagerAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {
        override fun getCount(): Int {
            return 5
        }

        override fun getItem(position: Int): Fragment {
            return ItemsFragment()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.drawing_menu -> {
                Log.v(tag, "Main menu")
                return true
            }
            R.id.options_menu -> {
                Log.v(tag, "Option Menu")
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}