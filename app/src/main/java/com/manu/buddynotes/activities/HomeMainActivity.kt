package com.manu.buddynotes.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.manu.buddynotes.R
import com.manu.buddynotes.databinding.ActivityHomeMainBinding

class HomeMainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityHomeMainBinding.inflate(layoutInflater) }
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        navController = findNavController(R.id.fragmentContainerView)
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}