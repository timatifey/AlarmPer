package dev.timatifey.navigation_demo.main_screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.timatifey.navigation_demo.databinding.Activity3Binding
import dev.timatifey.navigation_demo.utils.setupDrawer

class Activity3 : AppCompatActivity() {

    private lateinit var binding: Activity3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = Activity3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnToFirst.setOnClickListener {
            navToActivity1()
        }

        binding.btnToSecond.setOnClickListener {
            navToActivity2()
        }

        setupDrawer(binding.drawer, binding.drawerNav)
    }

    private fun navToActivity1() {
        startActivity(Intent(this, Activity1::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
    }

    private fun navToActivity2() {
        finish()
    }

}