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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_THIRD) {
            when (resultCode) {
                RESULT_CODE_TO_FIRST -> {
                    navToActivity1()
                }
                RESULT_CODE_TO_SECOND -> return
                else -> return
            }
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun navToActivity1() {
        finish()
    }

    private fun navToActivity3() {
        startActivityForResult(
            Intent(
                this,
                Activity3::class.java
            ), REQUEST_CODE_THIRD
        )
    }

    companion object {
        private const val REQUEST_CODE_THIRD = 100
        const val RESULT_CODE_TO_FIRST = REQUEST_CODE_THIRD + 1
        const val RESULT_CODE_TO_SECOND = REQUEST_CODE_THIRD + 2
    }
}