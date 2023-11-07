package com.akashdas.recipesearchapp

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get the NavController for your NavHostFragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController


        // Create an OnBackPressedCallback
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (navController.currentDestination?.id == R.id.registrationFragment ||
                    navController.currentDestination?.id == R.id.loginFragment ||
                    navController.currentDestination?.id == R.id.splashScreenFragment ||
                    navController.currentDestination?.id == R.id.homeFragment) {
                    // Show a confirmation dialog
                    showExitConfirmationDialog()

                } else {
                    // Otherwise, navigate up in the navigation stack
                    //isEnabled = false
                    //onBackPressedDispatcher.onBackPressed()
                }
            }
        }

        // Add the OnBackPressedCallback to the OnBackPressedDispatcher
        onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun showExitConfirmationDialog() {
        AlertDialog.Builder(this).apply {
            setTitle("Exit Application")
            setMessage("Are you sure you want to exit?")
            setPositiveButton("Yes") { _, _ ->
                finish()
            }
            setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            setCancelable(false)
            create()
            show()
        }
    }

}