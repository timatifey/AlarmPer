package dev.timatifey.alarmper.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import dev.timatifey.alarmper.R
import dev.timatifey.alarmper.screens.MainFragment
import dev.timatifey.alarmper.utils.setTransparent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Alarmper)
        window.setTransparent()
        setContentView(R.layout.activity_main)
        supportFragmentManager.commit {
            replace(R.id.activity_fragment_container, MainFragment())
        }
    }
}