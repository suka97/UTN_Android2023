package com.suka.superahorro.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.suka.superahorro.R
import com.suka.superahorro.activities.MainActivity
import com.suka.superahorro.database.AppDatabase
import com.suka.superahorro.database.UserDao

class LoginFragment : Fragment() {
    lateinit var v : View

    private var db: AppDatabase? = null
    private var usersDao: UserDao? = null
    lateinit var sharedPreferences: SharedPreferences

    lateinit var btLogin : Button
    lateinit var btSignup : Button
    lateinit var txtEmail : EditText
    lateinit var txtPass : EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_login, container, false)

        db = AppDatabase.getInstance(v.context)
        usersDao = db?.userDao()
        sharedPreferences = requireContext().getSharedPreferences("com.suka.superahorro.PREFERENCES", Context.MODE_PRIVATE)

        btLogin = v.findViewById(R.id.btLogin)
        btSignup = v.findViewById(R.id.btSignup)
        txtEmail = v.findViewById(R.id.txtEmail_login)
        txtPass = v.findViewById(R.id.txtPass_login)

        return v
    }


    fun login() {
        startActivity(Intent(context, MainActivity::class.java))
        requireActivity().finish()
    }


    override fun onStart() {
        super.onStart()

        val prefMail: String = sharedPreferences.getString("user_mail", "") ?:""
        val prefPass: String = sharedPreferences.getString("user_pass", "") ?:""
        val prefUser = usersDao?.fetchUserByCredentials(prefMail, prefPass)
        if ( prefUser != null ) login()

        btLogin.setOnClickListener() {
            val userMail = txtEmail.text.toString()
            val userPass = txtPass.text.toString()

            if ( userMail.isEmpty() || userPass.isEmpty() ) {
                Snackbar.make(v, "Debe completar todos los campos", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = usersDao?.fetchUserByCredentials(userMail, userPass)
            if ( user == null ) {
                Snackbar.make(v, "Usuario o contraseña incorrectos", Snackbar.LENGTH_SHORT).show()
            }
            else {
                val prefEditor = sharedPreferences.edit()
                prefEditor.putString("user_mail", userMail)
                prefEditor.putString("user_pass", userPass)
                prefEditor.apply()

                login()
            }
        }
        btSignup.setOnClickListener() {
            val action = LoginFragmentDirections.actionLoginFragmentToSignupFragment()
            findNavController().navigate(action)
        }
    }

}