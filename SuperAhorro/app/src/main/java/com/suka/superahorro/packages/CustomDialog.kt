package com.suka.superahorro.packages

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.text.InputType
import android.util.Log
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import com.suka.superahorro.R
import com.suka.superahorro.packages.*


fun createInputDialog(dialog: Dialog, desc: String, value: Any, onOkClicked: (String)->Unit ) {
    dialog.setContentView(R.layout.dialog_edit)
    dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

    val btOk : Button = dialog.findViewById(R.id.btOk)
    val btCancel : Button = dialog.findViewById(R.id.btCancel)
    val input : EditText = dialog.findViewById(R.id.inputDialogEdit)
    val txtDesc: TextView = dialog.findViewById(R.id.txtDialogEdit)

//    when (value) {
//        is String -> input.setText(value)
//        is UnitValue -> input.setText(value.value?.toString() ?: "")
//        else -> input.setText(value.toString())
//    }
    if ( value is String ) input.inputType = InputType.TYPE_CLASS_TEXT

    txtDesc.text = desc

    btOk.setOnClickListener{
        onOkClicked(input.text.toString())
        dialog.dismiss()
    }
    btCancel.setOnClickListener{
        dialog.dismiss()
    }

    dialog.setOnShowListener{
        Handler().postDelayed({
            input.requestFocus()
            input.setSelection(input.text.length)
            val imm = dialog.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(input, InputMethodManager.SHOW_IMPLICIT)
        }, 200)
    }
    dialog.show()

}