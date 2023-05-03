package com.suka.superahorro.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "users")
class User (mail : String, pass : String) {

    @PrimaryKey()
    @ColumnInfo(name = "mail")
    var mail: String = mail

    @ColumnInfo(name = "pass")
    var pass: String = pass

    @ColumnInfo(name = "firstname")
    var firstname: String? = null

    @ColumnInfo(name = "lastname")
    var lastname: String? = null

    @ColumnInfo(name = "picture")
    var picture: String? = null
}