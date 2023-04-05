package com.suka.firstapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    lateinit var label : TextView       // lateinit es una promeza de que lo voy a inicializar
    lateinit var btnShow : Button
    lateinit var btnHide : Button
    lateinit var btnColor1 : Button
    lateinit var btnColor2 : Button

    var labelText : String? = null      // puede ser null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        label = findViewById(R.id.txtLabel)
        btnShow = findViewById(R.id.btnShow)
        btnHide = findViewById(R.id.btnHide)
        btnColor1 = findViewById(R.id.btnColor1)
        btnColor2 = findViewById(R.id.btnColor2)

        labelText = "hola que tal"
        label.text = labelText

        btnShow.setOnClickListener {
            label.isVisible = true
        }
        btnHide.setOnClickListener {
            label.isVisible = false
        }
        btnColor1.setOnClickListener {
            label.setTextColor(Color.RED)
        }
        btnColor2.setOnClickListener {
            label.setTextColor(Color.BLUE)
        }
    }


}