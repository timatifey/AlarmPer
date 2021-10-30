package dev.timatifey.navigation_demo.main_screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import androidx.core.view.GravityCompat
import dev.timatifey.navigation_demo.R
import dev.timatifey.navigation_demo.about.ActivityAbout
import dev.timatifey.navigation_demo.databinding.Activity1Binding
import dev.timatifey.navigation_demo.utils.setupDrawer

class Activity1 : AppCompatActivity() {

    private lateinit var binding: Activity1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnToSecond.setOnClickListener {
            navToActivity2()
        }

        binding.btnToSecondNoHistory.setOnClickListener {
            navToActivity2NoHistory()
        }

        setupDrawer(binding.drawer, binding.drawerNav)
    }

    private fun navToActivity2() {
        startActivity(Intent(this, Activity2::class.java))
    }

    private fun navToActivity2NoHistory() {
        startActivity(
            Intent(this, Activity2::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        )
    }

}