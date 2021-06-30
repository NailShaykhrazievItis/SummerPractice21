package com.itis.summerpractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomSingleActivity : AppCompatActivity() {

    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_bottom)

        controller = (supportFragmentManager.findFragmentById(R.id.host_fragment) as NavHostFragment)
            .navController

        val bnvMain = findViewById<BottomNavigationView>(R.id.bnv_main)
        bnvMain.setupWithNavController(controller)
    }
}
