package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity2 : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("MyTag", "MainActivity : OnCreate")
        auth = Firebase.auth
        validateAndSignIn()
    }

    private fun validateAndSignIn() {
        if (auth.currentUser == null) {
            Log.i("MyTag", "Current user is null")
            startActivity(Intent(this, EmailPasswordActivity::class.java))
            Log.i("MyTag", "Starting EmailPasswordActivity")
        } else {
            Log.i("MyTag", "Current user is authenticated")
            val greetingTextView = findViewById<TextView>(R.id.tvHello)
            val inputField = findViewById<EditText>(R.id.etName)
            val submitButton = findViewById<Button>(R.id.btnSubmit)
            val offersButton = findViewById<Button>(R.id.btnOffers)
            var enteredName = ""
            submitButton.setOnClickListener {
                enteredName = inputField.text.toString()
                if(enteredName == "") {
                    offersButton.visibility = INVISIBLE
                    greetingTextView.text = ""
                    Toast.makeText(
                        this@MainActivity2,
                        "Please enter your name",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val message = "Welcome $enteredName"
                    greetingTextView.text = message
                    inputField.text.clear()
                    offersButton.visibility = VISIBLE
                }
            }
            offersButton.setOnClickListener {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("USER", enteredName)
                startActivity(intent)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("MyTag", "MainActivity : OnStart")
        validateAndSignIn()
    }

    override fun onResume() {
        super.onResume()
        Log.i("MyTag", "MainActivity : OnResume")
        validateAndSignIn()
    }

    override fun onPause() {
        super.onPause()
        Log.i("MyTag", "MainActivity : OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MyTag", "MainActivity : OnStop")
        auth.signOut()
    }

    override fun onDestroy() {
        super.onDestroy()
        auth.signOut()
        Log.i("MyTag", "MainActivity : OnDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("MyTag", "MainActivity : OnRestart")
    }
}