package com.journaler.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.AttributeSet
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.journaler.R
import com.journaler.databinding.ActivityMainBinding
import com.journaler.fragment.ItemsFragment
import com.journaler.navigation.NavigationDrawerAdapter
import com.journaler.navigation.NavigationDrawerItem
import com.journaler.perferences.PreferencesConfiguration
import com.journaler.perferences.PreferencesProvider
import com.journaler.service.MainService


class MainActivity : BaseActivity() {

    private lateinit var pager: ViewPager
    private lateinit var drawer_layout: DrawerLayout
    private lateinit var left_drawer: ListView

    override val tag = "Main activity"

    override fun getLayout() = R.layout.activity_main

    override fun getActivityTitle() = R.string.app_name

    private var service: MainService? = null

    private val keyPagePosition = "keyPagePosition"

    private val synchronize: NavigationDrawerItem by lazy {
        NavigationDrawerItem(
                getString(R.string.synchronize),
                Runnable { service?.synchronize() },
                false
        )
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {
            service = null
            synchronize.enabled = false
        }

        override fun onServiceConnected(p0: ComponentName?, binder: IBinder?) {
            if (binder is MainService.MainServiceBinder) {
                service = binder.getService()
                service?.let {
                    synchronize.enabled = true
                }
            }
        }
    }

    override fun getToolbar(): Int = R.id.main_toolbar

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        val view = super.onCreateView(name, context, attrs)

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        pager = findViewById(R.id.pager)
        drawer_layout = findViewById(R.id.drawer_layout)
        left_drawer = findViewById(R.id.left_drawer)

        val provider = PreferencesProvider()
        val config = PreferencesConfiguration("journaler_prefs", Context.MODE_PRIVATE)
        val preferences = provider.obtain(config, this)


        pager.adapter = ViewPagerAdapter(supportFragmentManager)
        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                // Ignore
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                // Ignore
            }

            override fun onPageSelected(position: Int) {
                Log.v(tag, "Page [ $position ]")
                preferences.edit().putInt(keyPagePosition, position).apply()
            }
        })

        val pagerPosition = preferences.getInt(keyPagePosition, 0)
        pager.setCurrentItem(pagerPosition, true)

        val menuItems = mutableListOf<NavigationDrawerItem>()
        val today = NavigationDrawerItem(
                getString(R.string.today),
                Runnable {
                    pager.setCurrentItem(0, true)
                }
        )

        val next7Days = NavigationDrawerItem(
                getString(R.string.next_seven_days),
                Runnable {
                    pager.setCurrentItem(1, true)
                }
        )

        val todos = NavigationDrawerItem(
                getString(R.string.todos),
                Runnable {
                    pager.setCurrentItem(2, true)
                }
        )

        val notes = NavigationDrawerItem(
                getString(R.string.notes),
                Runnable {
                    pager.setCurrentItem(3, true)
                }
        )

        menuItems.add(today)
        menuItems.add(next7Days)
        menuItems.add(todos)
        menuItems.add(notes)
        menuItems.add(synchronize)

        val navgationDrawerAdapter = NavigationDrawerAdapter(this, menuItems)
        left_drawer.adapter = navgationDrawerAdapter

        val serviceIntent = Intent(this, MainService::class.java)
        startService(serviceIntent)
    }

    override fun onResume() {
        super.onResume()
        val intent = Intent(this, MainService::class.java)
        bindService(intent, serviceConnection, android.content.Context.BIND_AUTO_CREATE)
    }

    override fun onPause() {
        super.onPause()
        unbindService(serviceConnection)
    }

    private class ViewPagerAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {
        override fun getItem(position: Int): Fragment {
            return ItemsFragment()
        }

        override fun getCount(): Int {
            return 4
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.drawing_menu -> {
                drawer_layout.openDrawer(GravityCompat.START)
                return true
            }
            R.id.options_menu -> {
                Log.v(tag, "Options menu.")
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}