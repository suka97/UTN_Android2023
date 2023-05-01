package com.suka.superahorro.packages

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class LayoutedInput (
    var fragment: Fragment,
    val fieldName: String,
    val layoutID: Int,
    val txtID: Int
){
    var inputLayout: ConstraintLayout
    var inputTxt: TextView

    init {
        val v: View = fragment.requireView()
        inputLayout = v.findViewById(layoutID)
        inputTxt = v.findViewById(txtID)
    }


    private val dialogShow: (v: View) -> Unit =  {
        val dialog = Dialog(fragment.requireContext())
        createInputDialog(dialog, fieldName, inputTxt.text) {
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private val touchListener = View.OnTouchListener { v, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
//                inputLayout.setBackgroundColor()
                true
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {

                true
            }
            else -> false
        }
    }


    fun updateListener() {
        inputLayout.isClickable = true
        inputLayout.setOnClickListener(dialogShow)
//        inputLayout.setOnTouchListener(touchListener)
        inputTxt.isClickable = true
        inputTxt.setOnClickListener(dialogShow)
//        inputTxt.setOnTouchListener(touchListener)
    }


    fun setText(txt: String) {
        inputTxt.text = txt
    }

}