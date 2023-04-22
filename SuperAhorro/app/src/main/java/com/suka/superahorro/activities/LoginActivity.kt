package com.suka.superahorro.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.suka.superahorro.R

class LoginActivity : AppCompatActivity() {
    lateinit var btLogin : Button
    lateinit var txtEmail : EditText
    lateinit var txtPass : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btLogin = findViewById(R.id.btLogin)
        txtEmail = findViewById(R.id.txtEmail_login)
        txtPass = findViewById(R.id.txtPass_login)

        btLogin.setOnClickListener() {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}