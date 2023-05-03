package com.suka.superahorro.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.suka.superahorro.R
import com.suka.superahorro.database.AppDatabase
import com.suka.superahorro.database.CartItemDao
import com.suka.superahorro.entities.CartItem
import com.suka.superahorro.packages.*

class ItemDetailFragment : Fragment() {
    lateinit var v : View

    private var db: AppDatabase? = null
    private var cartItemsDao: CartItemDao? = null
    lateinit var cartItem: CartItem

    lateinit var name : LayoutedInput
    lateinit var amount : LayoutedInput
    lateinit var price : LayoutedInput
    lateinit var total : LayoutedInput
    lateinit var brand : LayoutedInput
    lateinit var sku : LayoutedInput

    lateinit var layout : ConstraintLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_item_detail, container, false)
        val args = ItemDetailFragmentArgs.fromBundle(requireArguments())

        db = AppDatabase.getInstance(v.context)
        cartItemsDao = db?.cartItemDao()
        cartItem = cartItemsDao?.fetchCartItemById(args.itemID)!!

        layout = v.findViewById(R.id.layName_det)

        return v
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name = LayoutedInput(this, "Nombre", ::saveChanges, R.id.txtName_det, R.id.layName_det)
        amount = LayoutedInput(this, "Cantidad", ::saveChanges, R.id.txtAmount_det, R.id.layAmout_det)
        price = LayoutedInput(this, "Precio", ::saveChanges, R.id.txtPrice_det, R.id.layPrice_det)
        total = LayoutedInput(this, "Total", ::saveChanges, R.id.txtTot_det, R.id.layTot_det)
        brand = LayoutedInput(this, "Marca", ::saveChanges, R.id.txtBrand_det, R.id.layBrand_det)
        sku = LayoutedInput(this, "SKU", ::saveChanges, R.id.txtSku_det, R.id.laySku_det)
    }


    override fun onStart() {
        super.onStart()

        name.setText(cartItem.name)
        amount.setValue(UnitValue(cartItem.amount, GLOBAL_UNIT_AMOUNT))
        price.setValue(UnitValue(cartItem.unit_price, GLOBAL_UNIT_PRICE))
        total.setValue(UnitValue(cartItem.total_prince, GLOBAL_UNIT_PRICE))
        brand.setText(cartItem.brand ?: "-")
        sku.setText(cartItem.sku ?: "-")
    }

    override fun onResume() {
        super.onResume()

        name.updateListener()
        amount.updateListener()
        price.updateListener()
    }


    fun saveChanges(){
        cartItem.name = name.getText()
        cartItem.amount = amount.getValue()?.value
        cartItem.unit_price = price.getValue()?.value
        cartItem.total_prince = total.getValue()?.value
        cartItem.brand = brand.getText()
        cartItem.sku = sku.getText()

        cartItemsDao?.updateCartItem(cartItem)
    }

}