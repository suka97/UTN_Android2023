package com.suka.superahorro.database

import androidx.room.*
import com.suka.superahorro.entities.ShopItem

@Dao
interface ShopItemDao {

    @Query("SELECT * FROM shop_items")
    fun fetchAllCartItems(): MutableList<ShopItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCartItem(item: ShopItem)

    @Query("SELECT * FROM shop_items WHERE id = :id")
    fun fetchCartItemById(id: Long): ShopItem?
}