package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.gms.common.SignInButton

class AuthActivity : AppCompatActivity() {

    private lateinit var buttonEmailSignIn : Button
    private lateinit var buttonGoogleSignIn : SignInButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MyTag", "AuthActivity : OnCreate")
        setContentView(R.layout.activity_ui)
        buttonEmailSignIn = findViewById(R.id.button_email_sign_in)
        buttonGoogleSignIn = findViewById(R.id.button_google_sign_in)
        buttonEmailSignIn.setOnClickListener {
            startActivity(Intent(this, EmailPasswordActivity::class.java))
        }
        buttonGoogleSignIn.setOnClickListener {
            startActivity(Intent(this, GoogleAuthActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("MyTag", "AuthActivity : OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MyTag", "AuthActivity : OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("MyTag", "AuthActivity : OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MyTag", "AuthActivity : OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MyTag", "AuthActivity : OnDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("MyTag", "AuthActivity : OnRestart")
    }
}