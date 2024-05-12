package com.example.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.app.constants.CommonConstants.TAG
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity2 : BaseActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "MainActivity : OnCreate")
        auth = Firebase.auth
        validateAndSignIn()
    }

    override fun signOut() {
        if(auth.currentUser != null) {
            auth.signOut()
            Log.i(TAG, "Current user is not null")
            startActivity(Intent(this, AuthActivity::class.java))
            Log.i(TAG, "Starting AuthActivity")
        }
        signOutFromGoogle()
        Log.i(TAG, "Signed out from app")
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    private fun validateAndSignIn() {
        if (auth.currentUser == null) {
            Log.i(TAG, "Current user is null")
            startActivity(Intent(this, AuthActivity::class.java))
            Log.i(TAG, "Starting AuthActivity")
        } else {
            Log.i(TAG, "Current user is authenticated")
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
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "MainActivity : OnStart")
        validateAndSignIn()
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "MainActivity : OnResume")
        validateAndSignIn()
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "MainActivity : OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "MainActivity : OnStop")
        auth.signOut()
    }

    override fun onDestroy() {
        super.onDestroy()
        auth.signOut()
        signOutFromGoogle()
        Log.i(TAG, "MainActivity : OnDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "MainActivity : OnRestart")
    }

    private fun signOutFromGoogle() {
        val myApplication = application as MyApplication
        if(myApplication.googleSignInClient == null) {
            return
        }
        googleSignInClient = myApplication.googleSignInClient!!
        Log.i(TAG, "Signing out from Google")
        googleSignInClient.signOut().addOnCompleteListener(this) {
            Log.i(TAG, "Signed out from Google")
            Toast.makeText(
                this@MainActivity2,
                "Signed out from Google",
                Toast.LENGTH_SHORT
            ).show()
            startActivity(Intent(this, AuthActivity::class.java))
        }.addOnFailureListener(this) { e ->
            // An error occurred while signing out
            Log.e(TAG, "Sign out failed", e)
        }
    }

}