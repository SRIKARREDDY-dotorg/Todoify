package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.ToggleButton
import com.example.app.constants.CommonConstants.TAG
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class EmailPasswordActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var buttonSignUp : Button
    private lateinit var buttonLogIn : Button
    private lateinit var editTextEmail : EditText
    private lateinit var editTextPassword : EditText
    private lateinit var toggleShowPassword : ToggleButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        Log.i(TAG, "EmailPasswordActivity : OnCreate")
        setContentView(R.layout.activity_mainz)

        buttonSignUp = findViewById(R.id.buttonSignUp)
        buttonLogIn = findViewById(R.id.buttonLogin)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        toggleShowPassword = findViewById(R.id.toggleShowPassword)

        toggleShowPassword.setOnCheckedChangeListener { _, isChecked ->
            val selectionStart = editTextPassword.selectionStart
            val selectionEnd = editTextPassword.selectionEnd
            if (isChecked) {
                // Show password
                editTextPassword.transformationMethod = null
            } else {
                // Hide password
                editTextPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            }
            editTextPassword.setSelection(selectionStart, selectionEnd)
        }
        buttonSignUp.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            createAccount(email, password)
        }

        buttonLogIn.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            signIn(email, password)
        }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }

    private fun validEntries(email: String, password: String): Boolean {
        if(email.isEmpty()) {
            Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_LONG).show()
            return false
        }
        if(password.isEmpty()) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    private fun createAccount(email: String, password: String) {
        if(!validEntries(email, password)) return;

        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    sendEmailVerification()
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    updateUI(null, task.exception?.message)
                }
            }
        // [END create_user_with_email]
    }

    private fun signIn(email: String, password: String) {
        if(!validEntries(email, password)) return;

        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    updateUI(null, task.exception?.message)
                }
            }
        // [END sign_in_with_email]
    }


    private fun sendEmailVerification() {
        // [START send_email_verification]
        val user = auth.currentUser
        user?.sendEmailVerification()
            ?.addOnCompleteListener(this) { task ->
                if(task.isSuccessful) {
                    Toast.makeText(
                        baseContext,
                        "Verification email sent. Please check your email.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Log.e(TAG, "sendEmailVerification", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Failed to send verification email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        // [END send_email_verification]
    }

    private fun updateUI(user: FirebaseUser?, failureMessage: String? = null) {
        if (user != null) {
            if(user.isEmailVerified) {
                // User is signed in, navigate to the main activity or perform any desired action
                startActivity(Intent(this, MainActivity2::class.java))
                finish() // Finish the EmailPasswordActivity to prevent returning to it using the back button
            } else {
                // User email-id is not verified.
                Toast.makeText(
                    baseContext,
                    "Please verify your email to continue.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            // User is signed out or authentication failed
            // You can display a message to the user or perform any other desired action
            Toast.makeText(baseContext, failureMessage, Toast.LENGTH_SHORT).show()
            reload()
        }
    }

    private fun reload() {
        // Clear the input fields
        editTextEmail.text = null
        editTextPassword.text = null
    }
}