package com.suka.superahorro.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
class CartItem (name : String) {

    fun getTotalPrice() : Float? {
        if ( unit_price == null || amount == null ) return null
        return unit_price!! * amount!!
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0

    @ColumnInfo(name = "name")
    var name: String = name

    @ColumnInfo(name = "unit_price")
    var unit_price: Float? = null

    @ColumnInfo(name = "amount")
    var amount: Float? = null

    @ColumnInfo(name = "picture")
    var picture: String? = null


    @ColumnInfo(name = "brand")
    var brand: String? = null

    @ColumnInfo(name = "sku")
    var sku: String? = null

}