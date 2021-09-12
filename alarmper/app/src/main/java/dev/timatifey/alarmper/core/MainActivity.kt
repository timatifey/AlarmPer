package dev.timatifey.alarmper.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.commit
import dagger.hilt.android.AndroidEntryPoint
import dev.timatifey.alarmper.R
import dev.timatifey.alarmper.screens.MainFragment
import dev.timatifey.alarmper.utils.setTransparent

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setTheme(R.style.Theme_Alarmper)
        window.setTransparent()
        setContentView(R.layout.activity_main)
        supportFragmentManager.commit {
            replace(R.id.activity_fragment_container, MainFragment())
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    companion object {
        const val TAG = "MainActivity"
    }

}