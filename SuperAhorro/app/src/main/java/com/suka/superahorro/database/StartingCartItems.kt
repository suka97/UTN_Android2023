package com.suka.superahorro.database

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.suka.superahorro.R
import com.suka.superahorro.entities.CartItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader

class StartingCartItems(private val context: Context) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("StartingCartItems", "Pre-populating database...")
            //fillWithStartingCartItems(context)
            fillWithStartingUsersFromJson(context)
        }
    }

    override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
        super.onDestructiveMigration(db)
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("StartingCartItems", "Re-populating database...")
            //fillWithStartingCartItems(context)
            fillWithStartingUsersFromJson(context)
        }
    }

    /**
     * Pre-populate database with hard-coded users
     */
    private fun fillWithStartingCartItems(context: Context) {
        val items = listOf(
            CartItem("Manteca"),
            CartItem("Aceite de Oliva"),
            CartItem("Papel Higienico"),
        )
        val dao = AppDatabase.getInstance(context)?.cartItemDao()

        items.forEach {
            dao?.insertCartItem(it)
        }
    }

    /**
     * Pre-populate database with users from a Json file
     */
    private fun fillWithStartingUsersFromJson(context: Context) {
        val dao = AppDatabase.getInstance(context)?.cartItemDao()

        try {
            val items = loadJSONArray(context, R.raw.items)
            for (i in 0 until items.length()) {
                val item = items.getJSONObject(i)
                val newItem = CartItem(item.getString("name"))
                newItem.brand = item.getString("brand")
                newItem.unit_price = item.getDouble("price").toFloat()
                newItem.amount = item.getDouble("amount").toFloat()
                newItem.picture = item.getString("picture")

                dao?.insertCartItem(newItem)
            }
        } catch (e: JSONException) {
            Log.e("fillWithStartingNotes", e.toString())
        }
    }

    /**
     * Utility to load a JSON array from the raw folder
     */
    private fun loadJSONArray(context: Context, file: Int): JSONArray {
        val inputStream = context.resources.openRawResource(file)

        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }
}