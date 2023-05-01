package com.suka.superahorro.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suka.superahorro.R
import com.suka.superahorro.entities.ShopItem
import com.suka.superahorro.packages.toCurrency

class ShopItemAdapter (
    var shopItemsList:MutableList<ShopItem>,
    var onItemClick : (Int) -> Unit
) : RecyclerView.Adapter<ShopItemAdapter.PlayerHolder>() {
    class PlayerHolder (v: View) : RecyclerView.ViewHolder(v) {
        private var view: View
        init {
            this.view = v
        }

        fun getCard() : CardView {
            return view.findViewById(R.id.cardItem)
        }

        fun setPicture (picture_url: String?) {
            var img: ImageView = view.findViewById(R.id.img_item)
            if ( picture_url == null )
                img.setImageResource(R.drawable.default_item)
            else {
                Glide.with(img.context)
                    .load(picture_url)
                    .placeholder(R.drawable.default_item)
                    .into(img)
            }
        }

        fun setName (name: String?) {
            var txtName : TextView = view.findViewById(R.id.txtName_item)
            txtName.text = name
        }

        fun setPrice (price: Float?) {
            var txtPrice : TextView = view.findViewById(R.id.txtPrice_item)
            txtPrice.text = price.toCurrency()
        }

        fun setAmount (amount: Float?) {
            var txtAmount : TextView = view.findViewById(R.id.txtAmount_item)
            txtAmount.text = amount.toString()
        }

    }

    fun getItemTotal(): Float {
        var total: Float = 0F
        shopItemsList.forEach {
            total += it.total_prince?:0F
        }
        return total
    }

    fun getCartDescription(): String {
        return "Lista: $itemCount, Total: ${getItemTotal().toCurrency()}"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shop_item, parent, false)
        return (PlayerHolder(view))
    }

    override fun getItemCount(): Int {
        return shopItemsList.size
    }

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        holder.setPicture(shopItemsList[position].picture)
        holder.setName(shopItemsList[position].name)
        holder.setPrice(shopItemsList[position].unit_price)
        holder.setAmount(shopItemsList[position].amount)
        holder.getCard().setOnClickListener() {
            onItemClick(position)
        }
    }
}