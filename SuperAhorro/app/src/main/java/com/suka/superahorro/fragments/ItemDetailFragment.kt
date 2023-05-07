package com.suka.superahorro.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
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
        amount = LayoutedInput(this, "Cantidad", ::onUpdatePriceAmount, R.id.txtAmount_det, R.id.layAmout_det)
        price = LayoutedInput(this, "Precio", ::onUpdatePriceAmount, R.id.txtPrice_det, R.id.layPrice_det)
        total = LayoutedInput(this, "Total", ::onUpdateTotal, R.id.txtTot_det, R.id.layTot_det)
        brand = LayoutedInput(this, "Marca", ::saveChanges, R.id.txtBrand_det, R.id.layBrand_det)
        sku = LayoutedInput(this, "SKU", ::saveChanges, R.id.txtSku_det, R.id.laySku_det)
        setPicture(cartItem.picture)
    }


    override fun onStart() {
        super.onStart()

        name.setText(cartItem.name)
        amount.setValue(UnitValue(cartItem.amount, GLOBAL_UNIT_AMOUNT))
        price.setValue(UnitValue(cartItem.unit_price, GLOBAL_UNIT_PRICE))
        total.setValue(UnitValue(cartItem.getTotalPrice(), GLOBAL_UNIT_PRICE))
        brand.setText(cartItem.brand ?: "-")
        sku.setText(cartItem.sku ?: "-")
    }

    override fun onResume() {
        super.onResume()

        name.updateListener()
        amount.updateListener()
        price.updateListener()
    }


    fun onUpdateTotal(){
        val amount = amount.getValue()?.value
        val total = total.getValue()?.value
        if(amount != null && total != null){
            price.setValue(UnitValue(total / amount, GLOBAL_UNIT_PRICE))
        }

        saveChanges()
    }

    fun onUpdatePriceAmount(){
        val amount = amount.getValue()?.value
        val price = price.getValue()?.value
        if(amount != null && price != null){
            total.setValue(UnitValue(amount * price, GLOBAL_UNIT_PRICE))
        }

        saveChanges()
    }

    fun saveChanges(){
        cartItem.name = name.getText()
        cartItem.amount = amount.getValue()?.value
        cartItem.unit_price = price.getValue()?.value
        cartItem.brand = brand.getText()
        cartItem.sku = sku.getText()

        cartItemsDao?.updateCartItem(cartItem)
    }

    fun setPicture (picture_url: String?) {
        var img: ImageView = v.findViewById(R.id.img_det)
        if ( picture_url == null )
            img.setImageResource(R.drawable.default_item)
        else {
            Glide.with(img.context)
                .load(picture_url)
                .placeholder(R.drawable.default_item)
                .into(img)
        }
    }

}