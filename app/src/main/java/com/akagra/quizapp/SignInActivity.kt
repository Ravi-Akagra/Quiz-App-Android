package com.akagra.quizapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.akagra.quizapp.GoogleAuth
import com.akagra.quizapp.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


class SignInActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var googleAuth: GoogleAuth

    private var googleSignInBtn: SignInButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        googleSignInBtn = binding.googleSigninBtn
        googleAuth = GoogleAuth(this)
        println("crazzyyyyyyyy : ${googleAuth.acct?.idToken}")
        if (googleAuth.acct != null) {
            navigateToSecondActivity()
        }

        googleSignInBtn!!.setOnClickListener {
            signIn()
        }
    }

    fun signIn() {
        val intent = googleAuth.gsc!!.signInIntent
        println("intent : $intent")
        resultLauncher.launch(intent)
    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            println("I was here")
            val data: Intent? = result.data
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                task.getResult(ApiException::class.java)
                navigateToSecondActivity()
            } catch (e: ApiException) {
                print("asasasasa: ")
                println(e)
                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
            }
        }else{
            println("I was not here ${result.resultCode}")
            Toast.makeText(this,"Some error occured",Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToSecondActivity() {
        println("quizzzzzzzzz")
        val intent = Intent(this, StartTestActivity::class.java )
        startActivity(intent)
        finish()
    }

 }

