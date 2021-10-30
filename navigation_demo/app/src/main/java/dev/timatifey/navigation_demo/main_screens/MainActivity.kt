package dev.timatifey.navigation_demo.main_screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import dev.timatifey.navigation_demo.R
import dev.timatifey.navigation_demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(
            navController,
            AppBarConfiguration.Builder(navController.graph).build()
        )

        with(binding) {
            drawerNav.setNavigationItemSelectedListener {
                if (it.itemId == R.id.nav_about) {
                    drawer.closeDrawer(GravityCompat.START, true)
                    navController.navigate(R.id.action_to_activityAbout)
                }
                true
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}