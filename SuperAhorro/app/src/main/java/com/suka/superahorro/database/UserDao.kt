package com.suka.superahorro.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.suka.superahorro.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE mail = :mail AND pass = :pass")
    fun fetchUserByCredentials(mail: String, pass: String): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)
}