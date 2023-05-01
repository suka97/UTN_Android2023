package com.suka.superahorro.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "shop_items")
class ShopItem (id : Long, name : String) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = id

    @ColumnInfo(name = "name")
    var name: String = name

    @ColumnInfo(name = "unit_price")
    var unit_price: Float? = null

    @ColumnInfo(name = "amount")
    var amount: Float? = null

    @ColumnInfo(name = "picture")
    var picture: String? = null

    @ColumnInfo(name = "total_price")
    var total_prince: Float? = null
}