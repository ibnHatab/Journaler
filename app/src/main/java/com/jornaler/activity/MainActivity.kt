package com.jornaler.activity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ListView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.jornaler.R
import com.jornaler.databinding.ActivityMainBinding
import com.jornaler.navigation.NavigationDrawerAdapter
import com.jornaler.navigation.NavigationDrawerItem
import com.journaler.fragment.ItemsFragment


class MainActivity : BaseActivity() {
    override  val tag = "Main Activity"
    override fun getLayout(): Int = R.layout.activity_main
    override fun getActivityTitle() = R.string.app_name

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pager = findViewById<ViewPager>(R.id.pager)
        pager.adapter = ViewPagerAdapter(supportFragmentManager)

        val menuItems = mutableListOf<NavigationDrawerItem>()
        val today= NavigationDrawerItem(
            getString(R.string.today),
            kotlinx.coroutines.Runnable {
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

        val navgationDraweAdapter = NavigationDrawerAdapter(this, menuItems)
        val left_drawer = findViewById<ListView>(R.id.left_drawer)
        left_drawer.adapter = navgationDraweAdapter
    }

    @Suppress("DEPRECATION")
    private class ViewPagerAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {
        override fun getCount(): Int {
            return 4
        }

        override fun getItem(position: Int): Fragment {
            return ItemsFragment()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.drawing_menu -> {
                Log.v(tag, "Main menu")
                val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
                drawerLayout.openDrawer(GravityCompat.START)
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