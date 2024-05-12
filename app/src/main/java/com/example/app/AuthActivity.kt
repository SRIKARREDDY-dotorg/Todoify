package com.example.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.app.constants.CommonConstants.TAG
import com.google.android.gms.common.SignInButton

class AuthActivity : BaseActivity() {

    private lateinit var buttonEmailSignIn : Button
    private lateinit var buttonGoogleSignIn : SignInButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "AuthActivity : OnCreate")
        buttonEmailSignIn = findViewById(R.id.button_email_sign_in)
        buttonGoogleSignIn = findViewById(R.id.button_google_sign_in)
        buttonEmailSignIn.setOnClickListener {
            startActivity(Intent(this, EmailPasswordActivity::class.java))
        }
        buttonGoogleSignIn.setOnClickListener {
            startActivity(Intent(this, GoogleAuthActivity::class.java))
        }
    }

    override fun signOut() {
        Log.i(TAG, "No need to sign out")
        Toast.makeText(this, "Already signed out", Toast.LENGTH_SHORT).show()
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_ui
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "AuthActivity : OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "AuthActivity : OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "AuthActivity : OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "AuthActivity : OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "AuthActivity : OnDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "AuthActivity : OnRestart")
    }
}