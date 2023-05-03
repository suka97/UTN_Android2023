package com.suka.superahorro.database

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.suka.superahorro.entities.CartItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.io.BufferedReader

class StartingCartItems(private val context: Context) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("StartingCartItems", "Pre-populating database...")
            fillWithStartingCartItems(context)
        }
    }

    override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
        super.onDestructiveMigration(db)
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("StartingCartItems", "Re-populating database...")
            fillWithStartingCartItems(context)
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
//        val dao = AppDatabase.getInstance(context)?.userDao()
//
//        try {
//            val users = loadJSONArray(context, R.raw.users)
//            for (i in 0 until users.length()) {
//                val item = users.getJSONObject(i)
//                val user = User(
//                    id = 0,
//                    name = item.getString("name"),
//                    email = item.getString("email")
//                )
//
//                dao?.insertUser(user)
//            }
//        } catch (e: JSONException) {
//            Log.e("fillWithStartingNotes", e.toString())
//        }
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