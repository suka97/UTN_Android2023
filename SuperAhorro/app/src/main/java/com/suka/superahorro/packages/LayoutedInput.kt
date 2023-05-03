package com.suka.superahorro.packages

import android.annotation.SuppressLint
import android.app.Dialog
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.suka.superahorro.packages.*

class LayoutedInput (
    var fragment: Fragment,
    val fieldName: String,
    val onChangeListener: () -> Unit,
    txtID: Int,
    layoutID: Int? = null,
){
    var inputLayout: ConstraintLayout? = null
    var inputTxt: TextView
    var unitValue: UnitValue? = null

    init {
        val v: View = fragment.requireView()
        if (layoutID != null) inputLayout = v.findViewById(layoutID)
        inputTxt = v.findViewById(txtID)
    }


    private val dialogShow: (v: View) -> Unit =  {
        val dialog = Dialog(fragment.requireContext())
        createInputDialog(dialog, fieldName, unitValue ?: inputTxt.text) { newValue ->
            if (unitValue != null) setValue(UnitValue(newValue.toFloat(), unitValue!!.unit))
            else setText(newValue)

            onChangeListener()
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
        inputLayout?.isClickable = true
        inputLayout?.setOnClickListener(dialogShow)
//        inputLayout.setOnTouchListener(touchListener)
        inputTxt.isClickable = true
        inputTxt.setOnClickListener(dialogShow)
//        inputTxt.setOnTouchListener(touchListener)
    }


    fun setValue(value: UnitValue) {
        unitValue = value
        inputTxt.text = unitValue.toString()
        updateListener()
    }

    fun setText(text: String) {
        inputTxt.text = text
        updateListener()
    }


    fun getValue(): UnitValue? {
        return unitValue
    }

    fun getText(): String {
        return inputTxt.text.toString()
    }

}