package dev.timatifey.navigation_demo.main_screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.timatifey.navigation_demo.databinding.Activity2Binding
import dev.timatifey.navigation_demo.utils.setupDrawer

class Activity2 : AppCompatActivity() {

    private lateinit var binding: Activity2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnToFirst.setOnClickListener {
            navToActivity1()
        }

        binding.btnToThird.setOnClickListener {
            navToActivity3()
        }

        setupDrawer(binding.drawer, binding.drawerNav)
    }

    private fun navToActivity1() {
        finish()
    }

    private fun navToActivity3() {
        startActivity( Intent(this, Activity3::class.java))
    }
}