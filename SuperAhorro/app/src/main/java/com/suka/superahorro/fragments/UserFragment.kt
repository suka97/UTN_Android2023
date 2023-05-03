package com.suka.superahorro.fragments

import android.app.AlertDialog
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.suka.superahorro.R
import com.suka.superahorro.activities.LoginActivity
import com.suka.superahorro.activities.MainActivity

class UserFragment : Fragment() {
    lateinit var v : View

    lateinit var btLogout: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_user, container, false)

        btLogout = v.findViewById(R.id.btLogout)
        btLogout.setOnClickListener{
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Logout")
            builder.setMessage("¿Está seguro que desea desvincular la cuenta?")
            builder.setPositiveButton("Sí") { _, _ ->
                startActivity(Intent(context, LoginActivity::class.java))
                requireActivity().finish()
            }
            builder.setNegativeButton("No") { _, _ ->

            }
            val dialog = builder.create()
            dialog.show()
        }

        return v
    }

}