package com.suka.superahorro.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.suka.superahorro.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE mail = :mail AND pass = :pass")
    fun fetchUserByCredentials(mail: String, pass: String): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE mail = :mail")
    fun fetchUserByMail(mail: String): User?

    @Update
    fun updateUser(user: User)
}