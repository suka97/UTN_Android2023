package com.suka.superahorro.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.suka.superahorro.R
import com.suka.superahorro.entities.ShopItem
import java.text.NumberFormat

class ItemDetailFragment : Fragment() {

    lateinit var v : View
    lateinit var txtName : TextView
    lateinit var txtAmount : TextView
    lateinit var txtPrice : TextView

    lateinit var argItem : ShopItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_item_detail, container, false)

        var args = ItemDetailFragmentArgs.fromBundle(requireArguments())
        argItem = args.item

        txtName = v.findViewById(R.id.txtName_det)
        txtAmount = v.findViewById(R.id.txtAmount_det)
        txtPrice = v.findViewById(R.id.txtPrice_det)

        return v
    }


    override fun onStart() {
        super.onStart()

        val formatter = NumberFormat.getCurrencyInstance()
        txtName.text = argItem.name
        txtAmount.text = argItem.amount.toString()
        txtPrice.text = formatter.format(argItem.price)
    }

}