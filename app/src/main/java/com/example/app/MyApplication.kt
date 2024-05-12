package com.example.app

import android.app.Application
import com.google.android.gms.auth.api.signin.GoogleSignInClient

class MyApplication : Application() {

    var googleSignInClient: GoogleSignInClient? = null

}
