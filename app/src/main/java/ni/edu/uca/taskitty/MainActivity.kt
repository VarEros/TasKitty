package ni.edu.uca.taskitty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.content.res.Configuration
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.navigation.NavigationView
import ni.edu.uca.taskitty.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        drawerLayout = binding.mainActivity
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navView = findViewById<NavigationView>(R.id.nav_view)

        navView.itemIconTintList = null

        binding.included.imgMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        binding.included.logotype.setOnClickListener {
            navController.navigate(R.id.homeFragment)
        }



        navView.setNavigationItemSelectedListener{
            when(it.itemId) {
                R.id.itemCalendar -> {
                    if (!viewIsRepeated(R.id.calendarFragment))
                        navController.navigate(R.id.calendarFragment)
                }
                R.id.itemEvents -> {
                    if (!viewIsRepeated(R.id.eventListFragment))
                        navController.navigate(R.id.eventListFragment)
                }
                R.id.itemNotes -> {
                    if (!viewIsRepeated(R.id.notesListFragment))
                        navController.navigate(R.id.notesListFragment)
                }
                R.id.itemOng -> {
                    if (!viewIsRepeated(R.id.charityListFragment))
                        navController.navigate(R.id.charityListFragment)
                }
                R.id.itemAbout -> {
                    if (!viewIsRepeated(R.id.aboutUsFragment))
                        navController.navigate(R.id.aboutUsFragment)
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater

        inflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun viewIsRepeated(fragment: Int): Boolean {
        if(fragment == navController.currentDestination?.id)
            return true
        return false
    }


    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }
}
