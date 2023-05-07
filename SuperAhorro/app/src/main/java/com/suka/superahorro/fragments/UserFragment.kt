package com.suka.superahorro.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.suka.superahorro.R
import com.suka.superahorro.activities.LoginActivity
import com.suka.superahorro.activities.MainActivity
import com.suka.superahorro.database.AppDatabase
import com.suka.superahorro.database.UserDao
import com.suka.superahorro.entities.User

class UserFragment : Fragment() {
    lateinit var v : View

    private var db: AppDatabase? = null
    private var usersDao: UserDao? = null
    lateinit var sharedPreferences: SharedPreferences
    lateinit var saved_user: User

    lateinit var txtEmail: TextView
    lateinit var txtPass: EditText
    lateinit var btSave: Button
    lateinit var btLogout: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_user, container, false)

        db = AppDatabase.getInstance(v.context)
        usersDao = db?.userDao()
        sharedPreferences = requireContext().getSharedPreferences("com.suka.superahorro.PREFERENCES", Context.MODE_PRIVATE)

        txtEmail = v.findViewById(R.id.txtEmail_config)
        txtPass = v.findViewById(R.id.txtPass_config)
        btSave = v.findViewById(R.id.btSave_config)
        btLogout = v.findViewById(R.id.btLogout)

        return v
    }


    override fun onStart() {
        super.onStart()

        txtEmail.text = sharedPreferences.getString("user_mail", "")

        btSave.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Guardar")
            builder.setMessage("¿Está seguro que desea guardar los cambios?")
            builder.setPositiveButton("Sí") { _, _ ->
                val email = txtEmail.text.toString()
                val pass = txtPass.text.toString()
                usersDao?.updateUser(User(email, pass))
                sharedPreferences.edit().putString("user_pass", pass).apply()
                Snackbar.make(v, "Cambios guardados", Snackbar.LENGTH_SHORT).show()
            }
        }

        btLogout.setOnClickListener{
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Logout")
            builder.setMessage("¿Está seguro que desea desvincular la cuenta?")
            builder.setPositiveButton("Sí") { _, _ ->
                var sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("com.suka.superahorro.PREFERENCES", android.content.Context.MODE_PRIVATE)
                sharedPreferences.edit().clear().apply()

                startActivity(Intent(context, LoginActivity::class.java))
                requireActivity().finish()
            }
            builder.setNegativeButton("No") { _, _ ->

            }
            val dialog = builder.create()
            dialog.show()
        }
    }

}