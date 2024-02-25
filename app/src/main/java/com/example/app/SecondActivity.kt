package com.example.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MyTag", "MainActivity : OnCreate")
        setContentView(R.layout.activity_second)
        val userName = intent.getStringExtra("USER")
        val textView = findViewById<TextView>(R.id.tvOffer)
        val message = "$userName, you will get free access for ever"
        textView.text = message
    }

    override fun onStart() {
        super.onStart()
        Log.i("MyTag", "SecondActivity : OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MyTag", "SecondActivity : OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("MyTag", "SecondActivity : OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MyTag", "SecondActivity : OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MyTag", "SecondActivity : OnDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("MyTag", "SecondActivity : OnRestart")
    }
}