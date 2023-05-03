package com.suka.superahorro.database

import androidx.room.*
import com.suka.superahorro.entities.CartItem

@Dao
interface CartItemDao {

    @Query("SELECT * FROM cart_items")
    fun fetchAllCartItems(): MutableList<CartItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCartItem(item: CartItem)

    @Query("SELECT * FROM cart_items WHERE id = :id")
    fun fetchCartItemById(id: Long): CartItem?

    @Delete
    fun deleteCartItem(cartItem: CartItem)

    @Update
    fun updateCartItem(cartItem: CartItem)
}