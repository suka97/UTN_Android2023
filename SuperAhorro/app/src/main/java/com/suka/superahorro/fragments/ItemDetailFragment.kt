package com.suka.superahorro.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.suka.superahorro.R
import com.suka.superahorro.database.AppDatabase
import com.suka.superahorro.database.ShopItemDao
import com.suka.superahorro.entities.ShopItem
import com.suka.superahorro.packages.LayoutedInput
import com.suka.superahorro.packages.toCurrency
import java.text.NumberFormat

class ItemDetailFragment : Fragment() {

    lateinit var v : View

    private var db: AppDatabase? = null
    private var cartItemsDao: ShopItemDao? = null
    lateinit var cartItem: ShopItem

    lateinit var name : LayoutedInput
    lateinit var amount : LayoutedInput
    lateinit var price : LayoutedInput

    lateinit var layout : ConstraintLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_item_detail, container, false)
        val args = ItemDetailFragmentArgs.fromBundle(requireArguments())

        db = AppDatabase.getInstance(v.context)
        cartItemsDao = db?.shopItemDao()
        cartItem = cartItemsDao?.fetchCartItemById(args.itemID)!!

        layout = v.findViewById(R.id.layName_det)

        return v
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name = LayoutedInput(this, "Nombre", R.id.layName_det, R.id.txtName_det)
        amount = LayoutedInput(this, "Cantidad", R.id.layAmout_det, R.id.txtAmount_det)
        price = LayoutedInput(this, "Precio", R.id.layPrice_det, R.id.txtPrice_det)
    }


    override fun onStart() {
        super.onStart()

        name.setText(cartItem.name)
        amount.setText(cartItem.amount.toString())
        price.setText(cartItem.unit_price.toCurrency())
    }

    override fun onResume() {
        super.onResume()

        name.updateListener()
        amount.updateListener()
        price.updateListener()
    }

}