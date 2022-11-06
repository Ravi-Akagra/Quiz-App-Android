package com.akagra.quizapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.akagra.quizapp.databinding.ActivityStartTestBinding
import com.bumptech.glide.Glide

class StartTestActivity : AppCompatActivity() {
    private lateinit var binding:ActivityStartTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityStartTestBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val googleAuth = GoogleAuth(this)
        val img = googleAuth.acct?.photoUrl.toString()
        val username = "Username : ${googleAuth.acct?.displayName}"
        val email = "Email : ${googleAuth.acct?.email}"

        binding.username.text = username
        binding.email.text = email
        Glide.with(this).load(img).error(R.drawable.defaultimage).into(binding.userImage)

        binding.burger.setOnClickListener{
            AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Logout",
                    DialogInterface.OnClickListener { dialog, which ->
                        val googleAuth = GoogleAuth(this)
                        googleAuth.gsc?.signOut()
                        goToSignInActivity()
                    })
                .setNegativeButton("Cancel", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
        }

        binding.startTest.setOnClickListener {
            finish()
            val intent = Intent(this,TestActivity::class.java)
            startActivity(intent)
        }
    }

    private fun goToSignInActivity() {
        finish()
        val intent = Intent(this,SignInActivity::class.java)
        startActivity(intent)
    }
}