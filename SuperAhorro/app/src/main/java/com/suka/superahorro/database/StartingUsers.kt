package com.suka.superahorro.database

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.suka.superahorro.entities.CartItem
import com.suka.superahorro.entities.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StartingUsers(private val context: Context) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("StartingUsers", "Pre-populating database...")
            fillWithStartingCartItems(context)
        }
    }

    override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
        super.onDestructiveMigration(db)
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("StartingUsers", "Re-populating database...")
            fillWithStartingCartItems(context)
        }
    }

    /**
     * Pre-populate database with hard-coded users
     */
    private fun fillWithStartingCartItems(context: Context) {
        val items = listOf(
            User("mail1", "pass1"),
            User("mail2", "pass2"),
            User("mail3", "pass3"),
        )
        val dao = AppDatabase.getInstance(context)?.userDao()

        items.forEach {
            dao?.insertUser(it)
        }
    }
}