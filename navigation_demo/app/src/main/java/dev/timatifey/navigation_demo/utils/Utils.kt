package dev.timatifey.navigation_demo.utils

import android.app.Activity
import android.content.Intent
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import dev.timatifey.navigation_demo.R
import dev.timatifey.navigation_demo.about.ActivityAbout

fun Activity.setupDrawer(drawer: DrawerLayout, navigationView: NavigationView) {
    navigationView.setNavigationItemSelectedListener {
        if (it.itemId == R.id.nav_about) {
            drawer.closeDrawer(GravityCompat.START, true)
            startActivity(Intent(this, ActivityAbout::class.java))
        }
        true
    }
}