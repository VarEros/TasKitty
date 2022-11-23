package ni.edu.uca.taskitty

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    private lateinit var toogle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        drawerLayout = binding.mainActivity
        toogle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

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
                R.id.itemCalendar -> navController.navigate(R.id.calendarFragment)
                R.id.itemEvents -> navController.navigate(R.id.eventListFragment)
                R.id.itemNotes -> navController.navigate(R.id.notesListFragment)
                R.id.itemOng -> navController.navigate(R.id.charityListFragment)
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    fun accessTo() {

    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        toogle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toogle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toogle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }
}