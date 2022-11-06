package com.akagra.quizapp

import android.app.Activity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class GoogleAuth(activity: Activity) {
    private var gso: GoogleSignInOptions? = null
    var gsc: GoogleSignInClient? = null
    var acct:GoogleSignInAccount? = null

    init {
        gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        gsc = GoogleSignIn.getClient(activity, gso!!)
        acct = GoogleSignIn.getLastSignedInAccount(activity.applicationContext)
    }

    //TODO("Try to implement watch trailer")
    //TODO("Sign In Page beautification") SEE ARV CHATS
    //TODO SEARCH :tear:
    //TODO menu button

}