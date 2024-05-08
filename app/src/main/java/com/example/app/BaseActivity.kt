package com.example.app

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MyTag", "BaseActivity : OnCreate")
        setContentView(R.layout.activity_ui)
        val toolbar: MaterialToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        // TODO bug fix for support title bar
        supportActionBar?.title = ""
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        // Set up the hamburger button to open/close the drawer
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )

        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()

        // Handle clicks on navigation menu items
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    // Handle Home click
                    Log.i("MyTag", "Home clicked")

                    Handler().postDelayed({
                        Toast.makeText(this, "Home Clicked", Toast.LENGTH_SHORT).show()
                    }, 100)

                    // Close the drawer after handling the click
                    drawerLayout.closeDrawers()
                    true
                }
                R.id.nav_sign_out -> {
                    // Handle Sign Out click
                    Log.i("MyTag", "Sign Out Clicked")
                    Handler().postDelayed({
                        Toast.makeText(this, "Sign Out Clicked", Toast.LENGTH_SHORT).show()
                    }, 100)


                    // Close the drawer after handling the click
                    drawerLayout.closeDrawers()
                    true
                }
                else -> {
                    // Close the drawer after handling the click
                    Log.i("MyTag", "Nothing to close")
                    drawerLayout.closeDrawers()
                    false
                }
            }
        }
    }

    // Abstract method to be implemented by subclasses to provide layout resource id
    abstract fun getLayoutResourceId(): Int
}
